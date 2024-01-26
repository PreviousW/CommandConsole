import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val authorName = "FlagFan34272"
val pluginName = "socket"
val coreName = "SocketManager"
val pluginVersion = "1.0.0"

plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.lombok") version "1.9.10"
    id("io.freefair.lombok") version "8.1.0"
    application
}

group = "io.github.${authorName}.${pluginName}"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
    implementation("io.github.monun:kommand-api:3.1.6")
    implementation("io.github.monun:invfx-api:3.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.mongodb:mongodb-driver-sync:4.9.0")

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    processResources {
        filesMatching("plugin.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }

    test {
        useJUnitPlatform()
    }


    fun createPluginYml() {
        val pluginFile = rootProject.file("src/main/resources/plugin.yml")
        if (!pluginFile.exists()) {
            pluginFile.createNewFile()
        }
        pluginFile.writeText("""
            |name: ${pluginName}
            |version: ${pluginVersion}
            |main: io.github.${authorName}.${pluginName}.${coreName}
            |api-version: 1.20
            |libraries:
            |    - io.github.monun:kommand-core:3.1.6
            |    - io.github.monun:invfx-core:3.3.0
            |    - org.mongodb:mongodb-driver-sync:4.3.3
        """.trimMargin())
    }

    createPluginYml()
}



application {
    mainClass.set("Main")
}