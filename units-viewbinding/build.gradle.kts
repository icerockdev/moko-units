/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("dev.icerock.moko.gradle.android.library")
    id("dev.icerock.moko.gradle.detekt")
    id("dev.icerock.moko.gradle.android.publication")
}

android {
    buildFeatures.viewBinding = true
}

dependencies {
    implementation(libs.coroutines)

    api(projects.units)
    api(libs.recyclerView)
    api(libs.lifecycle)
}
