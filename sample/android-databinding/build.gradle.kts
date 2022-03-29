/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("android-app-convention")
    id("dev.icerock.mobile.multiplatform-units")
}

android {
    buildFeatures.dataBinding = true

    defaultConfig {
        applicationId = "dev.icerock.moko.samples.units"

        versionCode = 1
        versionName = "0.1.0"
    }

    lintOptions {
        disable("Instantiatable") // bug Error: SimpleActivity must extend android.app.Activity [Instantiatable]
    }
}

multiplatformUnits {
    classesPackage = "com.icerockdev"
    dataBindingPackage = "com.icerockdev"
    layoutsSourceSet = "main"
}

dependencies {
    implementation(libs.appCompat)
    implementation(libs.recyclerView)

    implementation(projects.sample.mppLibrary)
    implementation(projects.unitsDatabinding)
}
