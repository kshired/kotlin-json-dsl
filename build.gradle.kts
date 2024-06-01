import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm")
    id("com.vanniktech.maven.publish") version "0.28.0"
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

mavenPublishing {
    coordinates(
        groupId = projectGroup,
        artifactId = "kotlin-json-dsl",
        version = applicationVersion
    )

    pom {
        name.set("Kotlin Json DSL")
        description.set("Kotlin DSL library that makes it easy to generate json")
        inceptionYear.set("2024")
        url.set("https://github.com/kshired/kotlin-json-dsl")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("kshired")
                name.set("kshired")
                email.set("shiroed1211@gmail.com")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/kshired/kotlin-json-dsl.git")
            developerConnection.set("scm:git:ssh://github.com/kshired/kotlin-json-dsl.git")
            url.set("https://github.com/kshired/kotlin-json-dsl")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()
}
