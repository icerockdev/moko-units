/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

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
        classpath("gradle:units-build-logic:1")
    }
}

tasks.register("clean", Delete::class).configure {
    delete(rootProject.buildDir)
}
