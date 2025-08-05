import java.nio.file.Files

plugins {
    id("java")
    id("fabric-loom") version "1.11-SNAPSHOT"
}

group = "com.harleylizard"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://cursemaven.com")
    maven("https://maven.minecraftforge.net/")
    maven("https://mod-buildcraft.com/maven")
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.1")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.16.14")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.116.4+1.21.1")

    modRuntimeOnly("curse.maven:biomes-o-plenty-220318:6631073")

    modRuntimeOnly("com.github.glitchfiend:GlitchCore-fabric:1.21.1-2.1.0.0") {
        exclude(group = "net.fabricmc.fabric-api")
    }
    modRuntimeOnly("com.github.glitchfiend:TerraBlender-fabric:1.21.1-4.1.0.8") {
        exclude(group = "net.fabricmc.fabric-api")
    }

    modRuntimeOnly("alexiil.mc.mod:simplepipes-all:0.13.2") {
        exclude(group = "net.fabricmc.fabric-api")
    }

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

// resource processing
val processed = project.layout.buildDirectory.dir("processed")

sourceSets.main.get().resources.srcDir(processed.get().asFile)

sealed interface Template {

    fun process(modId: String, name: String, dest: String): FileCollection

    infix fun or(template: Template): Template

}

class TemplateSet(private val set: Set<Template>) : Template {

    override fun process(modId: String, name: String, dest: String): FileCollection {
        return files(set.flatMap { it.process(modId, name, dest) })
    }

    override infix fun or(template: Template) = TemplateSet(HashSet(set).also { it.add(template) })
}

class TemplateFile(private val path: java.nio.file.Path) : Template {

    override fun process(modId: String, name: String, dest: String): FileCollection {
        Files.newBufferedReader(path).use {
            val builder = StringBuilder()

            var line: String?
            while (it.readLine().also { line = it } != null) {
                builder.append(line!!.replace("{mod_id}", modId).replace("{name}", name)).append("\n")
            }

            writeTo(processed.get().asFile.toPath().resolve("$dest/${name}_${path.fileName}"), builder)
        }
        return files()
    }

    override infix fun or(template: Template) = TemplateSet(setOf(this, template))

    fun writeTo(to: java.nio.file.Path, result: StringBuilder) {
        to.parent.takeUnless(Files::isDirectory)?.let(Files::createDirectories)

        Files.newBufferedWriter(to).use {
            it.write(result.toString())
            it.flush()
        }
    }
}

fun asTemplate(path: String) = TemplateFile(project.layout.projectDirectory.file(path).asFile.toPath())

val log =
        asTemplate("template/block_state/log.json") or
        asTemplate("template/block_state/wood.json")

val wood =
            log
            asTemplate("template/block_state/planks.json") or
            asTemplate("template/block_state/stairs.json") or
            asTemplate("template/block_state/leaves.json")

val process = tasks.register("process") {
    group = "build"

    doLast {
        processed.get().asFile.takeUnless { it.exists() }?.mkdirs()

        wood.process("witches-way", "alder", "assets/witches-way/blockstates")
        log.process("witches-way", "stripped_alder", "assets/witches-way/blockstates")

        wood.process("witches-way", "hawthorn", "assets/witches-way/blockstates")
        log.process("witches-way", "stripped_hawthorn", "assets/witches-way/blockstates")

        wood.process("witches-way", "rowan", "assets/witches-way/blockstates")
        log.process("witches-way", "stripped_rowan", "assets/witches-way/blockstates")
    }
}

tasks.build {
    dependsOn(process)
}

tasks.named("processResources") {
    dependsOn(process)
}