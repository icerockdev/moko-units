/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    plugin(Deps.Plugins.androidLibrary)
    plugin(Deps.Plugins.kotlinMultiplatform)
    plugin(Deps.Plugins.mobileMultiplatform)
    plugin(Deps.Plugins.mokoUnits)
    plugin(Deps.Plugins.kotlinKapt)
    plugin(Deps.Plugins.iosFramework)
}

android.buildFeatures.dataBinding = true

dependencies {
    commonMainApi(Deps.Libs.MultiPlatform.mokoUnits.common)

    androidMainImplementation(Deps.Libs.Android.recyclerView)

    // fix of package javax.annotation does not exist import javax.annotation.Generated in DataBinding code
    androidMainCompileOnly("javax.annotation:jsr250-api:1.0")
}

multiplatformUnits {
    classesPackage = "com.icerockdev.library"
    dataBindingPackage = "com.icerockdev.library"
    layoutsSourceSet = "androidMain"
}

framework {
    export(project(":units"))
}
