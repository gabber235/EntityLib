plugins {
    `java-library`
    `maven-publish`
    signing
}

group = rootProject.group
version = rootProject.version
description = project.description

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.codemc.io/repository/maven-releases/")
    maven("https://repo.codemc.io/repository/maven-snapshots/")
}

val isShadow = project.pluginManager.hasPlugin("com.gradleup.shadow")

java {
    withSourcesJar()
    withJavadocJar()
    disableAutoTargetJvm()

    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release = 8
    }

    withType<Test> {
        failOnNoDiscoveredTests = false
    }

    processResources {
        inputs.property("version", project.version)
        filesMatching(listOf("plugin.yml", "velocity-plugin.json")) {
            expand("version" to project.version)
        }
    }

    jar {
        archiveClassifier = "default"
    }

    defaultTasks("build")
}

publishing {
    repositories {
        maven {
            name = "TypewriterBeta"
            url = uri("https://maven.typewritermc.com/beta")
            credentials(PasswordCredentials::class) {
                username = project.findProperty("typewriterBetaUsername") as String?
                password = project.findProperty("typewriterBetaPassword") as String?
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
        maven {
            name = "TypewriterReleases"
            url = uri("https://maven.typewritermc.com/releases")
            credentials(PasswordCredentials::class) {
                username = project.findProperty("typewriterReleasesUsername") as String?
                password = project.findProperty("typewriterReleasesPassword") as String?
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }

    publications {
        create<MavenPublication>("EntityLib") {
            groupId = project.group as String
            artifactId = project.name
            version = rootProject.ext["versionNoHash"].toString()
            from(components["java"])

            pom {
                name = "${rootProject.name}-${project.name}"
                description = rootProject.description
                url = "https://github.com/Tofaa2/EntityLib"

                licenses {
                    license {
                        name = "GPL-3.0"
                        url = "https://www.gnu.org/licenses/gpl-3.0.html"
                    }
                }

                developers {
                    developer {
                        id = "Tofaa2"
                        name = "Tofaa"
                        email = "tofaadev@gmail.com"
                    }
                }

                scm {
                    connection = "scm:git:https://github.com/Tofaa2/EntityLib.git"
                    developerConnection = "scm:git:https://github.com/Tofaa2/EntityLib.git"
                    url = "https://github.com/Tofaa2/EntityLib"
                }
            }
        }
    }
}

// So that SNAPSHOT is always the latest SNAPSHOT
configurations.all {
    resolutionStrategy.cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
}

val taskNames = gradle.startParameter.taskNames
if (taskNames.any { it.contains("build") }
    && taskNames.any { it.contains("publish") }) {
    throw IllegalStateException("Cannot build and publish at the same time.")
}