/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

buildscript {
    repositories {
        mavenLocal()

        jcenter()
        google()

        maven { url = uri("https://dl.bintray.com/kotlin/kotlin") }
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://dl.bintray.com/icerockdev/plugins") }
    }
    dependencies {
        plugin(Deps.Plugins.mokoUnits)
    }
}

allprojects {
    repositories {
        mavenLocal()

        google()
        jcenter()

        maven { url = uri("https://kotlin.bintray.com/kotlin") }
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
        maven { url = uri("https://dl.bintray.com/icerockdev/moko") }
    }

    configurations.all {
        resolutionStrategy.dependencySubstitution {
            substitute(module(Deps.Libs.MultiPlatform.mokoUnits.common))
                .with(project(":units"))
            substitute(module(Deps.Libs.MultiPlatform.mokoUnitsBasic.common))
                .with(project(":units-basic"))
        }
    }

    plugins.withId(Deps.Plugins.androidLibrary.id) {
        configure<com.android.build.gradle.LibraryExtension> {
            compileSdkVersion(Deps.Android.compileSdk)

            defaultConfig {
                minSdkVersion(Deps.Android.minSdk)
                targetSdkVersion(Deps.Android.targetSdk)
            }
        }
    }

    tasks.matching {
        it.name == "publishToMavenLocal"
    }.configureEach {
        val pluginPublish = gradle.includedBuild("plugins")
            .task(":units-generator:publishToMavenLocal")
        dependsOn(pluginPublish)
    }
}

tasks.register("clean", Delete::class).configure {
    delete(rootProject.buildDir)
}
