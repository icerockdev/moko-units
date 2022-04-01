/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("dev.icerock.moko.gradle.multiplatform.mobile")
    id("dev.icerock.mobile.multiplatform.ios-framework")
    id("dev.icerock.moko.gradle.detekt")
}

dependencies {
    commonMainApi(projects.units)

    androidMainImplementation(libs.recyclerView)

    commonTestImplementation(libs.mokoTest)
    commonTestImplementation(projects.unitsTest)
}

framework {
    export(projects.units)
}
