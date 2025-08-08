import java.nio.file.Files

plugins {
    id("java")
    id("fabric-loom") version "1.11-SNAPSHOT"
}

group = "com.harleylizard"
version = "1.1-alpha"

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

    fun process(name: String, path: java.nio.file.Path)

    infix fun or(template: Template): Template

}

class TemplateSet(private val set: MutableSet<Template>) : Template {

    override fun process(name: String, path: java.nio.file.Path) {
        for (template in set) {
            template.process(name, path)
        }
    }

    override infix fun or(template: Template) = this.also { it.set += template }

}

class TemplateFile(private val template: java.nio.file.Path) : Template {

    override fun process(name: String, path: java.nio.file.Path) {
        if (!Files.isRegularFile(template)) {
            logger.info("Cannot find file ${template}")
        }

        Files.newBufferedReader(template).use {
            val builder = StringBuilder()

            var line: String?
            while (it.readLine().also { line = it } != null) {
                builder.append(line!!.replace("{mod_id}", "witches-way").replace("{name}", name)).append("\n")
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

    fun Template.process(name: String) {
        process(name, path)
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

var colouredBlocks =
        asTemplate("template/block/stone_altar.json") or
        asTemplate("template/block/stone_altar_clothed.json") or
        asTemplate("template/block/mohair.json") or
        asTemplate("template/block/bloody_mohair.json")

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
            wood.process("alder")
            log.process("alder")
            log.process("stripped_alder")

            wood.process("hawthorn")
            log.process("hawthorn")
            log.process("stripped_hawthorn")

            wood.process("rowan")
            log.process("rowan")
            log.process("stripped_rowan")

            coloured.process("white")
            coloured.process("light_gray")
            coloured.process("gray")
            coloured.process("black")
            coloured.process("brown")
            coloured.process("red")
            coloured.process("orange")
            coloured.process("yellow")
            coloured.process("lime")
            coloured.process("green")
            coloured.process("cyan")
            coloured.process("light_blue")
            coloured.process("blue")
            coloured.process("purple")
            coloured.process("magenta")
            coloured.process("pink")
        }

        group("assets/witches-way/models/block") {
            woodBlocks.process("alder")
            logBlocks.process("stripped_alder")

            woodBlocks.process("hawthorn")
            logBlocks.process("stripped_hawthorn")

            woodBlocks.process("rowan")
            logBlocks.process("stripped_rowan")

            colouredBlocks.process("white")
            colouredBlocks.process("light_gray")
            colouredBlocks.process("gray")
            colouredBlocks.process("black")
            colouredBlocks.process("brown")
            colouredBlocks.process("red")
            colouredBlocks.process("orange")
            colouredBlocks.process("yellow")
            colouredBlocks.process("lime")
            colouredBlocks.process("green")
            colouredBlocks.process("cyan")
            colouredBlocks.process("light_blue")
            colouredBlocks.process("blue")
            colouredBlocks.process("purple")
            colouredBlocks.process("magenta")
            colouredBlocks.process("pink")
        }

        group("assets/witches-way/models/item") {
            colouredItems.process("white")
            colouredItems.process("light_gray")
            colouredItems.process("gray")
            colouredItems.process("black")
            colouredItems.process("brown")
            colouredItems.process("red")
            colouredItems.process("orange")
            colouredItems.process("yellow")
            colouredItems.process("lime")
            colouredItems.process("green")
            colouredItems.process("cyan")
            colouredItems.process("light_blue")
            colouredItems.process("blue")
            colouredItems.process("purple")
            colouredItems.process("magenta")
            colouredItems.process("pink")
        }

        group("data/witches-way/recipe") {
            planks.process("alder")
            planks.process("hawthorn")
            planks.process("rowan")
        }
    }
}

tasks.build {
    dependsOn(process)
}

tasks.named("processResources") {
    dependsOn(process)
}