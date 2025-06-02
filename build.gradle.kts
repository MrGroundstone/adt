import java.util.Properties
import org.gradle.internal.os.OperatingSystem

plugins {
    id("java")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

val libraryProperties = Properties().apply {
    load(rootProject.file("release.properties").inputStream())
}

version = if (project.hasProperty("githubReleaseTag")) {
    project.property("githubReleaseTag").toString().drop(1)
} else {
    libraryProperties.getProperty("prettyVersion")
}

val libName = "adt-informatik"

group = "com.Wolfgang"
repositories {
    mavenCentral()
    maven { url = uri("https://jogamp.org/deployment/maven/") }
}

dependencies {
    compileOnly(group = "org.processing", name = "core", version = "4.3.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
tasks.jar {
    archiveBaseName.set(libName)
    archiveClassifier.set("")
    archiveVersion.set("")
}

tasks.test {
    useJUnitPlatform()
}


tasks.build.get().mustRunAfter("clean")

tasks.register("buildReleaseArtifacts") {
    group = "processing"
    dependsOn("clean","build", "writeLibraryProperties")
    finalizedBy("packageRelease", "duplicateZipToPdex")

    doFirst {
        println("Releasing library $libName")
        println(org.gradle.internal.jvm.Jvm.current())

        println("Cleaning release...")
        project.delete(files(releaseRoot))
    }

    doLast {
        println("Creating package...")

        println("Copy library...")
        copy {
            from(layout.buildDirectory.file("libs/${libName}.jar"))
            into("$releaseDirectory/library")
        }

        println("Copy dependencies...")
        copy {
            from(configurations.runtimeClasspath)
            into("$releaseDirectory/library")
        }

        println("Copy additional artifacts...")
        copy {
            from(rootDir)
            include("README.md", "readme/**", "library.properties", "examples/**", "src/**")

            into(releaseDirectory)
            exclude("*.DS_Store", "**/networks/**")
        }

        println("Copy repository library.txt...")
        copy {
            from(rootDir)
            include("library.properties")
            into(releaseRoot)
            rename("library.properties", "$libName.txt")
        }
    }
}
