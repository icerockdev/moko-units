/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

object Versions {
    object Android {
        const val compileSdk = 28
        const val targetSdk = 28
        const val minSdk = 16
    }

    const val kotlin = "1.3.70"

    private const val mokoUnits = "0.3.0"

    object Plugins {
        const val android = "3.6.1"

        const val kotlin = Versions.kotlin
        const val mokoUnits = Versions.mokoUnits
    }

    object Libs {
        object Android {
            const val appCompat = "1.1.0"
            const val recyclerView = "1.0.0"
        }

        object MultiPlatform {
            const val mokoUnits = Versions.mokoUnits
            const val mokoGraphics = "0.2.0"
            const val mokoResources = "0.8.0"
        }

        object Jvm {
            const val kotlinPoet = "1.3.0"
        }
    }
}
