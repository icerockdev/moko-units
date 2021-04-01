/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import java.util.Base64
import kotlin.text.String

plugins {
    plugin(Deps.Plugins.javaGradle)
    plugin(Deps.Plugins.kotlinJvm)
    plugin(Deps.Plugins.mavenPublish)
    plugin(Deps.Plugins.signing)
}

repositories {
    mavenCentral()
    google()

    jcenter {
        content {
            includeGroup("org.jetbrains.trove4j")
            includeModule("org.jetbrains.kotlinx", "kotlinx-html-jvm")
        }
    }
}

group = "dev.icerock.moko"
version = Deps.mokoUnitsVersion

dependencies {
    compileOnly(Deps.Libs.Jvm.androidGradlePlugin)
    implementation(Deps.Libs.Jvm.kotlinPoet)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

publishing {
    repositories.maven("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") {
        name = "OSSRH"

        credentials {
            username = System.getenv("OSSRH_USER")
            password = System.getenv("OSSRH_KEY")
        }
    }
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            // Provide artifacts information requited by Maven Central
            pom {
                name.set("MOKO units")
                description.set("Composing units into list and show in RecyclerView/UITableView/UICollectionView. Control your lists from common code for mobile (android & ios) Kotlin Multiplatform development")
                url.set("https://github.com/icerockdev/moko-units")
                licenses {
                    license {
                        url.set("https://github.com/icerockdev/moko-units/blob/master/LICENSE.md")
                    }
                }

                developers {
                    developer {
                        id.set("Alex009")
                        name.set("Aleksey Mikhailov")
                        email.set("aleksey.mikhailov@icerockdev.com")
                    }
                    developer {
                        id.set("ATchernov")
                        name.set("Andrey Tchernov")
                        email.set("tchernov@icerockdev.com")
                    }
                }

                scm {
                    connection.set("scm:git:ssh://github.com/icerockdev/moko-units.git")
                    developerConnection.set("scm:git:ssh://github.com/icerockdev/moko-units.git")
                    url.set("https://github.com/icerockdev/moko-units")
                }
            }

            signing {
                val signingKeyId: String? = System.getenv("SIGNING_KEY_ID")
                val signingPassword: String? = System.getenv("SIGNING_PASSWORD")
                val signingKey: String? = System.getenv("SIGNING_KEY")?.let { base64Key ->
                    String(Base64.getDecoder().decode(base64Key))
                }
                if (signingKeyId != null) {
                    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
                    sign(publishing.publications)
                }
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
