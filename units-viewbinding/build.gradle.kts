/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("android-library-convention")
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
