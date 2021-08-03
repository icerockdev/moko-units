/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("jvm-convention")
    id("publication-convention")

    id("com.gradle.plugin-publish") version ("0.15.0")
    id("detekt-convention")
    id("java-gradle-plugin")
}

group = "dev.icerock.moko"
version = libs.versions.mokoUnitsVersion.get()

dependencies {
    implementation(gradleKotlinDsl())

    compileOnly(libs.androidGradlePlugin)
    implementation(libs.kotlinPoet)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
}

gradlePlugin {
    plugins {
        create("units-generator") {
            id = "dev.icerock.mobile.units-generator"
            implementationClass = "dev.icerock.moko.units.plugin.UnitsGeneratorPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/icerockdev/moko-units"
    vcsUrl = "https://github.com/icerockdev/moko-units"
    description = "Plugin to codegen for new Units"
    tags = listOf("moko-units", "moko", "kotlin", "kotlin-multiplatform")

    plugins {
        getByName("units-generator") {
            displayName = "MOKO Units generator plugin"
        }
    }

    mavenCoordinates {
        groupId = project.group as String
        artifactId = project.name
        version = project.version as String
    }
}
