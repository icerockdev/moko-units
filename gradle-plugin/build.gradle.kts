/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    `kotlin-dsl`
    `maven-publish`
}

repositories {
    mavenLocal()

    jcenter()
    google()
}

dependencies {
    implementation(Deps.Libs.Jvm.kotlinPoet)
    compileOnly(Deps.Plugins.android)
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

publishing {
    repositories.maven("https://api.bintray.com/maven/icerockdev/plugins/moko-units-generator/;publish=1") {
        name = "bintray"

        credentials {
            username = System.getProperty("BINTRAY_USER")
            password = System.getProperty("BINTRAY_KEY")
        }
    }

    publications {
        register("plugin", MavenPublication::class) {
            groupId = "dev.icerock.moko"
            artifactId = "units-generator"
            version = Versions.Libs.MultiPlatform.mokoUnits

            from(components["java"])
        }
    }
}
