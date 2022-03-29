/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()

    gradlePluginPortal()

    jcenter {
        content {
            includeGroup("org.jetbrains.trove4j")
        }
    }
}

dependencies {
    api(libs.kotlinGradlePlugin)
    api(libs.androidGradlePlugin)
    api(libs.googleServicesGradlePlugin)
    api(libs.mokoGradlePlugin)
    api(libs.mobileMultiplatformGradlePlugin)
    api(libs.kotlinSerializationGradlePlugin)

//    api(libs.kotlinGradlePlugin)
//    api(libs.androidGradlePlugin)
//    api(libs.mokoGradlePlugin)
//    api(libs.mobileMultiplatformGradlePlugin)
}
