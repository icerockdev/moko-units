/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("android-library-convention")
}

android {
    buildFeatures.dataBinding = true
}

dependencies {
    implementation(libs.appCompat)
    api(libs.recyclerView)
    api(projects.units)

    // fix of package javax.annotation does not exist import javax.annotation.Generated in DataBinding code
    compileOnly(libs.javaxAnnotation)
}

