/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("mpp-library-convention")
    id("kotlin-kapt")
    id("dev.icerock.mobile.multiplatform-units")
    id("dev.icerock.mobile.multiplatform.ios-framework")
}

android.buildFeatures.dataBinding = true

dependencies {
    commonMainApi(libs.mokoUnits)

    androidMainImplementation(libs.recyclerView)

    // fix of package javax.annotation does not exist import javax.annotation.Generated in DataBinding code
    androidMainCompileOnly("javax.annotation:jsr250-api:1.0")

    commonTestImplementation(libs.mokoTest)
    commonTestImplementation(projects.ubitsTests)
}

multiplatformUnits {
    classesPackage = "com.icerockdev.library"
    dataBindingPackage = "com.icerockdev.library"
    layoutsSourceSet = "androidMain"
}

framework {
    export(project(":units"))
}
