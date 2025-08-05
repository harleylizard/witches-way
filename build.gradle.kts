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