/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("com.android.library")
    id("android-base-convention")
}

android {
    sourceSets.configureEach { java.srcDir("src/$name/kotlin") }
}