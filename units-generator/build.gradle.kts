/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("jvm-convention")
    id("publication-convention")
}

dependencies {
    implementation(gradleKotlinDsl())

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
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
}
