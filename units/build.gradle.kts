/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("mpp-library-convention")
    id("kotlin-kapt")
    id("stub-javadoc-convention")
    id("publication-convention")
}

android.buildFeatures.dataBinding = true

kotlin {
    sourceSets {
        val iosArm64Main by getting
        val iosX64Main by getting

        iosArm64Main.dependsOn(iosX64Main)
    }
}

dependencies {
    androidMainImplementation(Deps.Libs.Android.appCompat)
    androidMainApi(Deps.Libs.Android.recyclerView)

    // fix of package javax.annotation does not exist import javax.annotation.Generated in DataBinding code
    androidMainCompileOnly("javax.annotation:jsr250-api:1.0")
}
