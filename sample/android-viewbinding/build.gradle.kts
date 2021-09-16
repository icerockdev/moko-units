/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("android-app-convention")
    id("detekt-convention")
}

android {
    buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "dev.icerock.moko.samples.units"

        versionCode = 1
        versionName = "0.1.0"
    }

    lintOptions {
        disable("Instantiatable") // bug Error: SimpleActivity must extend android.app.Activity [Instantiatable]
    }
}

dependencies {
    implementation(libs.constraintLayout)
    implementation(libs.appCompat)
    implementation(projects.unitsViewbinding)
    implementation(projects.sample.mppLibrary)
}
