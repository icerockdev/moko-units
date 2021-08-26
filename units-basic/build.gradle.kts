/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("mpp-library-convention")
    id("stub-javadoc-convention")
    id("publication-convention")
    id("detekt-convention")
}

dependencies {
    commonMainApi(projects.units)

    androidMainImplementation(libs.appCompat)

    commonMainApi(libs.mokoGraphics)
    commonMainApi(libs.mokoResources)
}
