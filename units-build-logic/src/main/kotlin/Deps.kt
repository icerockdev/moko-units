/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

object Deps {
    private const val kotlinVersion = "1.4.31"
    private const val androidGradleVersion = "4.0.1"

    private const val androidAppCompatVersion = "1.1.0"
    private const val androidCoreTestingVersion = "2.1.0"
    private const val recyclerViewVersion = "1.0.0"

    private const val kotlinPoetVersion = "1.3.0"

    private const val coroutinesVersion = "1.4.2"
    private const val mokoGraphicsVersion = "0.6.1"
    private const val mokoResourcesVersion = "0.15.1"
    private const val mokoTestVersion = "0.2.1"
    const val mokoUnitsVersion = "0.5.0"

    object Android {
        const val compileSdk = 29
        const val targetSdk = 29
        const val minSdk = 16
    }

    object Plugins {
        val javaGradle = GradlePlugin(id = "java-gradle-plugin")
        val androidApplication = GradlePlugin(id = "com.android.application")
        val androidLibrary = GradlePlugin(id = "com.android.library")
        val kotlinMultiplatform = GradlePlugin(id = "org.jetbrains.kotlin.multiplatform")
        val kotlinJvm = GradlePlugin(id = "org.jetbrains.kotlin.jvm")
        val kotlinKapt = GradlePlugin(id = "kotlin-kapt")
        val kotlinAndroid = GradlePlugin(id = "kotlin-android")
        val mavenPublish = GradlePlugin(id = "org.gradle.maven-publish")

        val mobileMultiplatform = GradlePlugin(id = "dev.icerock.mobile.multiplatform")
        val iosFramework = GradlePlugin(id = "dev.icerock.mobile.multiplatform.ios-framework")

        val mokoUnits = GradlePlugin(
            id = "dev.icerock.mobile.multiplatform-units",
            module = "dev.icerock.moko:units-generator:$mokoUnitsVersion"
        )

        val signing = GradlePlugin(id = "signing")
    }

    object Libs {
        object Android {
            const val appCompat = "androidx.appcompat:appcompat:$androidAppCompatVersion"
            const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"
        }

        object MultiPlatform {
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
            val mokoUnits = MultiPlatformLibrary(
                common = "dev.icerock.moko:units:$mokoUnitsVersion",
                iosX64 = "dev.icerock.moko:units-iosx64:$mokoUnitsVersion",
                iosArm64 = "dev.icerock.moko:units-iosarm64:$mokoUnitsVersion"
            )
            val mokoUnitsBasic = MultiPlatformLibrary(
                common = "dev.icerock.moko:units-basic:$mokoUnitsVersion",
                iosX64 = "dev.icerock.moko:units-basic-iosx64:$mokoUnitsVersion",
                iosArm64 = "dev.icerock.moko:units-basic-iosarm64:$mokoUnitsVersion"
            )
            val mokoGraphics = MultiPlatformLibrary(
                common = "dev.icerock.moko:graphics:$mokoGraphicsVersion",
                iosX64 = "dev.icerock.moko:graphics-iosx64:$mokoGraphicsVersion",
                iosArm64 = "dev.icerock.moko:graphics-iosarm64:$mokoGraphicsVersion"
            )
            val mokoResources = MultiPlatformLibrary(
                common = "dev.icerock.moko:resources:$mokoResourcesVersion",
                iosX64 = "dev.icerock.moko:resources-iosx64:$mokoResourcesVersion",
                iosArm64 = "dev.icerock.moko:resources-iosarm64:$mokoResourcesVersion"
            )
        }

        object Tests {
            const val kotlinTestJUnit =
                "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
            const val androidCoreTesting =
                "androidx.arch.core:core-testing:$androidCoreTestingVersion"
            const val mokoTest =
                "dev.icerock.moko:test:$mokoTestVersion"
        }

        object Jvm {
            const val kotlinPoet =
                "com.squareup:kotlinpoet:$kotlinPoetVersion"
            const val androidGradlePlugin =
                "com.android.tools.build:gradle:$androidGradleVersion"
        }
    }
}
