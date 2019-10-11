/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
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

        project.afterEvaluate {
            val generatedDir = generateTask.generationPath
            generatedDir.mkdirs()

            val exts = project.extensions
            val buildProperties: BaseExtension = exts.findByType(AppExtension::class.java)
                ?: exts.findByType(LibraryExtension::class.java)
                ?: return@afterEvaluate

            buildProperties.sourceSets.getByName("main").java.srcDirs(generatedDir)

            tasks.getByName("preBuild").dependsOn(generateTask)
        }
    }
}
