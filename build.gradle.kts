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

    modImplementation("vazkii.patchouli:Patchouli:1.21.1-92-FABRIC") {
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

val templated = project.layout.buildDirectory.dir("templated")

sourceSets.main.get().resources.srcDir(templated.get().asFile)

sealed interface Template {

    fun process(modId: String, name: String, path: java.nio.file.Path)

    infix fun or(template: Template): Template

}

class TemplateSet(private val set: MutableSet<Template>) : Template {

    override fun process(modId: String, name: String, path: java.nio.file.Path) {
        for (template in set) {
            template.process(modId, name, path)
        }
    }

    override infix fun or(template: Template) = this.also { it.set += template }

}

class TemplateFile(private val template: java.nio.file.Path) : Template {

    override fun process(modId: String, name: String, path: java.nio.file.Path) {
        if (!Files.isRegularFile(template)) {
            logger.info("Cannot find file ${template}")
        }

        Files.newBufferedReader(template).use {
            val builder = StringBuilder()

            var line: String?
            while (it.readLine().also { line = it } != null) {
                builder.append(line!!.replace("{mod_id}", modId).replace("{name}", name)).append("\n")
            }

            writeTo(path.resolve("${name}_${template.fileName}"), builder)
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

class Group(private val path: java.nio.file.Path) {

    fun Template.process(modId: String, name: String) {
        process(modId, name, path)
    }

}

inline fun group(path: String, unit: Group.() -> Unit) = Group(templated.get().asFile.toPath().resolve(path)).also(unit)

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

val coloured =
        asTemplate("template/block_state/stone_altar.json") or
        asTemplate("template/block_state/mohair.json") or
        asTemplate("template/block_state/bloody_mohair.json")

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

var colouredItems =
        asTemplate("template/item/stone_altar.json") or
        asTemplate("template/item/mohair.json") or
        asTemplate("template/item/bloody_mohair.json")

var planks = asTemplate("template/recipe/planks.json")

val process = tasks.register("process") {
    group = "build"

    doLast {
        templated.get().asFile.takeUnless { it.exists() }?.mkdirs()

        group("assets/witches-way/blockstates") {
            wood.process("witches-way", "alder")
            log.process("witches-way", "alder")
            log.process("witches-way", "stripped_alder")

            wood.process("witches-way", "hawthorn")
            log.process("witches-way", "hawthorn")
            log.process("witches-way", "stripped_hawthorn")

            wood.process("witches-way", "rowan")
            log.process("witches-way", "rowan")
            log.process("witches-way", "stripped_rowan")

            coloured.process("witches-way", "white")
            coloured.process("witches-way", "light_gray")
            coloured.process("witches-way", "gray")
            coloured.process("witches-way", "black")
            coloured.process("witches-way", "brown")
            coloured.process("witches-way", "red")
            coloured.process("witches-way", "orange")
            coloured.process("witches-way", "yellow")
            coloured.process("witches-way", "lime")
            coloured.process("witches-way", "green")
            coloured.process("witches-way", "cyan")
            coloured.process("witches-way", "light_blue")
            coloured.process("witches-way", "blue")
            coloured.process("witches-way", "purple")
            coloured.process("witches-way", "magenta")
            coloured.process("witches-way", "pink")
        }

        group("assets/witches-way/models/block") {
            woodBlocks.process("witches-way", "alder")
            logBlocks.process("witches-way", "stripped_alder")

            woodBlocks.process("witches-way", "hawthorn")
            logBlocks.process("witches-way", "stripped_hawthorn")

            woodBlocks.process("witches-way", "rowan")
            logBlocks.process("witches-way", "stripped_rowan")

        }

        group("assets/witches-way/models/item") {
            colouredItems.process("witches-way", "white")
            colouredItems.process("witches-way", "light_gray")
            colouredItems.process("witches-way", "gray")
            colouredItems.process("witches-way", "black")
            colouredItems.process("witches-way", "brown")
            colouredItems.process("witches-way", "red")
            colouredItems.process("witches-way", "orange")
            colouredItems.process("witches-way", "yellow")
            colouredItems.process("witches-way", "lime")
            colouredItems.process("witches-way", "green")
            colouredItems.process("witches-way", "cyan")
            colouredItems.process("witches-way", "light_blue")
            colouredItems.process("witches-way", "blue")
            colouredItems.process("witches-way", "purple")
            colouredItems.process("witches-way", "magenta")
            colouredItems.process("witches-way", "pink")

        }

        group("data/witches-way/recipe") {
            planks.process("witches-way", "alder")
            planks.process("witches-way", "hawthorn")
            planks.process("witches-way", "rowan")

        }
    }
}

tasks.build {
    dependsOn(process)
}

tasks.named("processResources") {
    dependsOn(process)
}