/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.7.1"
}

buildscript {
    repositories {
        mavenCentral()
        google()

        jcenter {
            content {
                includeGroup("org.jetbrains.trove4j")
            }
        }
    }
    dependencies {
        classpath("dev.icerock.moko:units-generator")
        classpath(":units-build-logic")
    }
}

allprojects {
    plugins.withId("org.gradle.maven-publish") {
        group = "dev.icerock.moko"
        version = libs.versions.mokoUnitsVersion.get()
    }
}

val sampleProjects: Set<Project> = project(":sample").allprojects

apiValidation {
    ignoredProjects.addAll(sampleProjects.map { it.name })
}
