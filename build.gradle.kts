import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.41"
}

group = "org.battleplugins.generator"
version = "1.1.0"

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/public")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile("org.spigotmc", "spigot-api", "1.16.1-R0.1-SNAPSHOT")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

task("run", JavaExec::class) {
    main = "org.battleplugins.generator.ResourceGenerator"
    classpath = sourceSets["main"].runtimeClasspath
}