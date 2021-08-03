/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("mpp-library-convention")
    id("stub-javadoc-convention")
    id("publication-convention")
    id("detekt-convention")
}

group = "dev.icerock.moko"
version = libs.versions.mokoUnitsVersion.get()

dependencies {
    commonMainApi(projects.units)

    androidMainImplementation(libs.appCompat)
    androidMainImplementation(libs.recyclerView)
}
