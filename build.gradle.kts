/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

//buildscript {
//    repositories {
//        mavenCentral()
//        google()
//
//        gradlePluginPortal()
//
//        jcenter {
//            content {
//                includeGroup("org.jetbrains.trove4j")
//            }
//        }
//    }
//    dependencies {
//        //classpath(":units-build-logic")
//        //classpath("dev.icerock.moko:units-generator")
//        classpath(libs.kotlinGradlePlugin)
//        classpath(libs.androidGradlePlugin)
//        classpath(libs.googleServicesGradlePlugin)
//        classpath(libs.mokoGradlePlugin)
//        classpath(libs.mobileMultiplatformGradlePlugin)
//        classpath(libs.kotlinSerializationGradlePlugin)
//    }
//}

allprojects {
    plugins.withId("org.gradle.maven-publish") {
        group = "dev.icerock.moko"
        version = libs.versions.mokoUnitsVersion.get()
    }
}

val sampleProjects: Set<Project> = project(":sample").allprojects
