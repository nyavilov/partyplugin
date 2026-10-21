plugins {
    id("com.gradleup.shadow") version "9.6.1"
    id("java-library")
    alias(libs.plugins.run.paper)
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.momirealms.net/snapshots")
}

dependencies {
    compileOnly(libs.paper.api)
    implementation("net.momirealms:sparrow-ui:beta.38")

    compileOnly("org.projectlombok:lombok:1.18.48")
    annotationProcessor("org.projectlombok:lombok:1.18.48")

    compileOnly("org.jetbrains:annotations:26.1.0")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
    runServer {
        minecraftVersion(libs.versions.minecraft.get())
        jvmArgs("-Xms2G", "-Xmx2G")
    }

    processResources {
        val props = mapOf("version" to version, "description" to project.description)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}

tasks.shadowJar {
    relocate("net.momirealms.sparrow.ui", "com.example.myplugin.libraries.sparrow.ui")
}
