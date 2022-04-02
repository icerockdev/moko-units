/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.plugin.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File

class DropDownUnitItemGenerator(
    className: String,
    classPackage: String,
    dataBindingPackage: String,
    layoutName: String,
    outBindingClass: UnitItemGenerator
) : UnitItemGenerator(
    className = className,
    classPackage = classPackage,
    dataBindingPackage = dataBindingPackage,
    layoutName = layoutName,
    variables = outBindingClass.variables,
    imports = emptyList()
) {
    private val parentPackage: String = outBindingClass.classPackage
    private val parentName: String = outBindingClass.className
    private val interfaceName =
        ClassName.bestGuess("dev.icerock.moko.units.databinding.DataBindingDropDownUnitItem")

    override fun generate(generationDir: File) {
        val unitClass = TypeSpec.classBuilder(className)

        unitClass.superclass(ClassName(parentPackage, parentName))
        unitClass.addSuperinterface(interfaceName)
        unitClass.addProperty(
            PropertySpec.builder("dropDownLayoutId", Int::class, KModifier.OVERRIDE)
                .initializer("R.layout.$layoutName")
                .build()
        )

        unitClass.addFunction(
            FunSpec.builder("bindDropDown")
                .addParameter(
                    ParameterSpec.builder(
                        "viewDataBinding",
                        ClassName.bestGuess("androidx.databinding.ViewDataBinding")
                    ).build()
                )
                .addModifiers(KModifier.OVERRIDE)
                .addCode(buildBindCode())
                .build()
        )

        val typeSpec = unitClass.build()

        val fileSpec = FileSpec.get(
            packageName = classPackage,
            typeSpec = typeSpec
        ).toBuilder().apply {
            imports.forEach { import ->
                val type = ClassName.bestGuess(import)
                addImport(type.packageName, type.simpleName)
            }
            addImport(dataBindingPackage, "BR")
        }.build()

        fileSpec.writeTo(generationDir)
    }
}
