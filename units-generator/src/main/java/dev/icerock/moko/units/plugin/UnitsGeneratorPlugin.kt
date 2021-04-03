/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.plugin

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.BasePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class UnitsGeneratorPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extensionContainer = project.extensions
        val config = extensionContainer.create("multiplatformUnits", Configuration::class.java)

        val generateTask = project.tasks.create(
            "generateUnitsClasses",
            GenerateBindingClassesTask::class.java
        )
        generateTask.configuration = config

        val generatedDir = generateTask.generationPath
        generatedDir.mkdirs()

        project.plugins
            .matching { it is BasePlugin }
            .configureEach {
                val baseExtension = project.extensions
                    .getByName("android") as BaseExtension

                baseExtension.sourceSets
                    .matching { it.name == "main" }
                    .configureEach {
                        it.java.srcDirs(generatedDir)
                    }

                project.tasks.getByName("preBuild").dependsOn(generateTask)
            }
    }
}
