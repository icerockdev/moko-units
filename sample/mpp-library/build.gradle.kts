/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("mpp-library-convention")
    id("kotlin-kapt")
    id("dev.icerock.mobile.multiplatform-units")
    id("dev.icerock.mobile.multiplatform.ios-framework")
    id("detekt-convention")
}

android.buildFeatures.dataBinding = true

dependencies {
    commonMainApi(projects.units)

    "androidMainImplementation"(libs.recyclerView)

    // fix of package javax.annotation does not exist import javax.annotation.Generated in DataBinding code
    "androidMainCompileOnly"(libs.javaxAnnotation)

    commonTestImplementation(libs.mokoTest)
    commonTestImplementation(projects.unitsTest)
}

multiplatformUnits {
    classesPackage = "com.icerockdev.library"
    dataBindingPackage = "com.icerockdev.library"
    layoutsSourceSet = "androidMain"
}

framework {
    export(projects.units)
}
