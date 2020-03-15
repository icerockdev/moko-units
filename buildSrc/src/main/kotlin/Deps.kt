/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

object Deps {
    object Plugins {
        const val android =
            "com.android.tools.build:gradle:${Versions.Plugins.android}"
        const val mokoUnits =
            "dev.icerock.moko:units-generator:${Versions.Plugins.mokoUnits}"
    }

    object Libs {
        object Android {
            val kotlinStdLib = AndroidLibrary(
                name = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
            )
            val appCompat = AndroidLibrary(
                name = "androidx.appcompat:appcompat:${Versions.Libs.Android.appCompat}"
            )
            val recyclerView = AndroidLibrary(
                name = "androidx.recyclerview:recyclerview:${Versions.Libs.Android.recyclerView}"
            )
        }

        object MultiPlatform {
            val kotlinStdLib = MultiPlatformLibrary(
                android = Android.kotlinStdLib.name,
                common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}"
            )
            val mokoUnits = MultiPlatformLibrary(
                common = "dev.icerock.moko:units:${Versions.Libs.MultiPlatform.mokoUnits}",
                iosX64 = "dev.icerock.moko:units-iosx64:${Versions.Libs.MultiPlatform.mokoUnits}",
                iosArm64 = "dev.icerock.moko:units-iosarm64:${Versions.Libs.MultiPlatform.mokoUnits}"
            )
            val mokoUnitsBasic = MultiPlatformLibrary(
                common = "dev.icerock.moko:units-basic:${Versions.Libs.MultiPlatform.mokoUnits}",
                iosX64 = "dev.icerock.moko:units-basic-iosx64:${Versions.Libs.MultiPlatform.mokoUnits}",
                iosArm64 = "dev.icerock.moko:units-basic-iosarm64:${Versions.Libs.MultiPlatform.mokoUnits}"
            )
            val mokoGraphics = MultiPlatformLibrary(
                common = "dev.icerock.moko:graphics:${Versions.Libs.MultiPlatform.mokoGraphics}",
                iosX64 = "dev.icerock.moko:graphics-iosx64:${Versions.Libs.MultiPlatform.mokoGraphics}",
                iosArm64 = "dev.icerock.moko:graphics-iosarm64:${Versions.Libs.MultiPlatform.mokoGraphics}"
            )
            val mokoResources = MultiPlatformLibrary(
                common = "dev.icerock.moko:resources:${Versions.Libs.MultiPlatform.mokoResources}",
                iosX64 = "dev.icerock.moko:resources-iosx64:${Versions.Libs.MultiPlatform.mokoResources}",
                iosArm64 = "dev.icerock.moko:resources-iosarm64:${Versions.Libs.MultiPlatform.mokoResources}"
            )
        }

        object Jvm {
            const val kotlinPoet = "com.squareup:kotlinpoet:${Versions.Libs.Jvm.kotlinPoet}"
        }
    }

    val plugins: Map<String, String> = mapOf(
        "dev.icerock.mobile.multiplatform-units" to Plugins.mokoUnits
    )
}