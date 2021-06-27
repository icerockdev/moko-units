/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("jvm-convention")
    id("publication-convention")
}

group = "dev.icerock.moko"
version = libs.versions.mokoUnitsVersion.get()

dependencies {
    implementation(gradleKotlinDsl())

    compileOnly(libs.androidGradlePlugin)
    implementation(libs.kotlinPoet)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
}
