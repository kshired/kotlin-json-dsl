plugins {
    kotlin("jvm")
    `maven-publish`
}

val projectGroup: String by project
val applicationVersion: String by project
group = projectGroup
version = applicationVersion

repositories {
    mavenCentral()
}

dependencies {
    val kotestVersion: String by project
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}
