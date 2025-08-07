import java.nio.file.Files

plugins {
    id("java")
    id("fabric-loom") version "1.11-SNAPSHOT"
}

group = "com.harleylizard"
version = "1.0-alpha"

repositories {
    mavenCentral()
    maven("https://cursemaven.com")
    maven("https://maven.minecraftforge.net/")
    maven("https://mod-buildcraft.com/maven")
    maven("https://maven.blamejared.com")
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

    modApi("vazkii.patchouli:Patchouli:1.21.1-92-FABRIC") {
        exclude(group = "net.fabricmc.fabric-api")
    }

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

val processed = project.layout.buildDirectory.dir("processed")

sourceSets.main.get().resources.srcDir(processed.get().asFile)

sealed interface Template {

    fun process(modId: String, name: String, dest: String)

    infix fun or(template: Template): Template

}

class TemplateSet(private val set: MutableSet<Template>) : Template {

    override fun process(modId: String, name: String, dest: String) {
        for (template in set) {
            template.process(modId, name, dest)
        }
    }

    override infix fun or(template: Template) = this.also { it.set += template }
}

class TemplateFile(private val path: java.nio.file.Path) : Template {

    override fun process(modId: String, name: String, dest: String) {
        if (!Files.isReadable(path)) {
            logger.info("Path not found $path")

            return
        }

        Files.newBufferedReader(path).use {
            val builder = StringBuilder()

            var line: String?
            while (it.readLine().also { line = it } != null) {
                builder.append(line!!.replace("{mod_id}", modId).replace("{name}", name)).append("\n")
            }

            writeTo(processed.get().asFile.toPath().resolve("$dest/${name}_${path.fileName}"), builder)
        }
    }

    override infix fun or(template: Template) = TemplateSet(mutableSetOf(this, template))

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
            log or
            asTemplate("template/block_state/planks.json") or
            asTemplate("template/block_state/stairs.json") or
            asTemplate("template/block_state/slab.json") or
            asTemplate("template/block_state/leaves.json") or
            asTemplate("template/block_state/sapling.json")

val logBlocks =
        asTemplate("template/block/log.json") or
        asTemplate("template/block/log_horizontal.json") or
        asTemplate("template/block/wood.json")

val woodBlocks =
            logBlocks or
            asTemplate("template/block/planks.json") or
            asTemplate("template/block/stairs.json") or
            asTemplate("template/block/stairs_inner.json") or
            asTemplate("template/block/stairs_outer.json") or
            asTemplate("template/block/slab.json") or
            asTemplate("template/block/slab_top.json")

val process = tasks.register("process") {
    group = "build"

    doLast {
        processed.get().asFile.takeUnless { it.exists() }?.mkdirs()

        wood.process("witches-way", "alder", "assets/witches-way/blockstates")
        woodBlocks.process("witches-way", "alder", "assets/witches-way/models/block")

        log.process("witches-way", "stripped_alder", "assets/witches-way/blockstates")
        logBlocks.process("witches-way", "stripped_alder", "assets/witches-way/models/block")

        wood.process("witches-way", "hawthorn", "assets/witches-way/blockstates")
        woodBlocks.process("witches-way", "hawthorn", "assets/witches-way/models/block")

        log.process("witches-way", "stripped_hawthorn", "assets/witches-way/blockstates")
        logBlocks.process("witches-way", "stripped_hawthorn", "assets/witches-way/models/block")

        wood.process("witches-way", "rowan", "assets/witches-way/blockstates")
        woodBlocks.process("witches-way", "rowan", "assets/witches-way/models/block")

        log.process("witches-way", "stripped_rowan", "assets/witches-way/blockstates")
        logBlocks.process("witches-way", "stripped_rowan", "assets/witches-way/models/block")
    }
}

tasks.build {
    dependsOn(process)
}

tasks.named("processResources") {
    dependsOn(process)
}