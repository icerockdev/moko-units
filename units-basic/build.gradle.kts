/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("dev.icerock.moko.gradle.multiplatform.mobile")
    id("dev.icerock.moko.gradle.detekt")
    id("dev.icerock.moko.gradle.publication")
    id("dev.icerock.moko.gradle.stub.javadoc")
}

dependencies {
    commonMainApi(projects.units)

    androidMainImplementation(libs.appCompat)

    commonMainApi(libs.mokoGraphics)
    commonMainApi(libs.mokoResources)
}
