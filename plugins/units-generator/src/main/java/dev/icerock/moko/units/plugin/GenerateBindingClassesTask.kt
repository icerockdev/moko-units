/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.plugin

import dev.icerock.moko.units.plugin.generator.DropDownUnitItemGenerator
import dev.icerock.moko.units.plugin.generator.UnitItemGenerator
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.w3c.dom.Element
import org.xml.sax.SAXException
import java.io.File
import java.io.IOException
import java.util.*
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

open class GenerateBindingClassesTask : DefaultTask() {
    @get:Internal
    lateinit var configuration: Configuration
    @get:Internal
    val generationPath by lazy { File(project.buildDir, "generated/moko/units/src/") }

    internal enum class BindingClassType {
        SIMPLE,
        DROPDOWN
    }

    init {
        group = "moko-units"
    }

    @TaskAction
    @Throws(
        ParserConfigurationException::class,
        SAXException::class,
        IOException::class
    )
    internal fun generate() {
        val classPackage = configuration.classesPackage
        val dataBindingPackage = configuration.dataBindingPackage
        val sourceSet = configuration.layoutsSourceSet

        cleanOldGeneratedFiles(generationPath)

        val xmlPath = File(project.projectDir, "/src/$sourceSet/res")

        generateBindingClasses(xmlPath, generationPath, classPackage, dataBindingPackage)
    }

    private fun cleanOldGeneratedFiles(packagePath: File) {
        val files = packagePath.listFiles() ?: return

        for (oldFile in files) {
            if (oldFile.isDirectory) {
                cleanOldGeneratedFiles(oldFile)
            }

            if (!oldFile.delete()) {
                println("can't delete file $oldFile")
            }
        }
    }

    @Throws(
        IOException::class,
        ParserConfigurationException::class,
        SAXException::class
    )
    private fun generateBindingClasses(
        xmlPath: File,
        classesGenerationPath: File,
        classPackage: String,
        dataBindingPackage: String
    ) {
        val files = xmlPath.listFiles() ?: return

        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()

        val simpleFileList = mutableListOf<File>()
        val dropDownFileList = mutableListOf<File>()

        for (layoutFolder in files) {
            if (!layoutFolder.name.startsWith(LAYOUT_RES_PACKAGE)) continue

            val xmlFiles = layoutFolder.listFiles() ?: continue

            for (xmlFile in xmlFiles) {
                val type = getLayoutType(xmlFile)

                when (type) {
                    BindingClassType.SIMPLE -> simpleFileList.add(xmlFile)
                    BindingClassType.DROPDOWN -> dropDownFileList.add(xmlFile)
                }

            }
        }

        val simpleGenerators = simpleFileList
            .mapNotNull {
                getUnitItemGenerator(
                    dBuilder = dBuilder,
                    xmlFile = it,
                    classPackage = classPackage,
                    dataBindingPackage = dataBindingPackage
                )
            }
        val dropDownGenerators = dropDownFileList
            .mapNotNull {
                getDropDownUnitItemGenerator(
                    xmlFile = it,
                    bindingClasses = simpleGenerators,
                    classPackage = classPackage,
                    dataBindingPackage = dataBindingPackage
                )
            }

        simpleGenerators.plus(dropDownGenerators).forEach { it.generate(classesGenerationPath) }
    }

    private fun getLayoutType(xmlFile: File): BindingClassType {
        val separatedLayoutName = getSeparatedLayoutName(xmlFile)
        val lastPartOfName = separatedLayoutName[separatedLayoutName.size - 1]

        return if (lastPartOfName.contentEquals(LAYOUT_DROPDOWN_SUFFIX)) {
            BindingClassType.DROPDOWN
        } else {
            BindingClassType.SIMPLE
        }
    }

    private fun getLayoutName(layoutFile: File): String {
        return layoutFile.name.replace(".xml", "")
    }

    private fun getSeparatedLayoutName(layoutFile: File): Array<String> {
        return getLayoutName(layoutFile)
            .split(LAYOUT_NAME_DELIMITER.toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()
    }

    private fun getBindingClassName(xmlFile: File): String {
        val separatedLayoutName = getSeparatedLayoutName(xmlFile)
        val fileNameBuilder = StringBuilder()
        for (subName in separatedLayoutName) {
            val partOfName = subName.substring(0, 1).toUpperCase() + subName.substring(1)
            fileNameBuilder.append(partOfName)
        }
        return fileNameBuilder.toString()
    }

    @Throws(IOException::class, SAXException::class)
    private fun getUnitItemGenerator(
        dBuilder: DocumentBuilder,
        xmlFile: File,
        classPackage: String,
        dataBindingPackage: String
    ): UnitItemGenerator? {
        val doc = dBuilder.parse(xmlFile)
        doc.documentElement.normalize()

        val firstChild = doc.firstChild

        if (firstChild?.nodeName?.contentEquals(BINDING_LAYOUT) != true) return null

        val mapVariables = HashMap<String, String>()
        val importList = ArrayList<String>()

        if (firstChild is Element) {
            val elements = firstChild.getElementsByTagName(BINDING_DATA)
            if (elements != null) {
                for (i in 0 until elements.length) {
                    val dataNode = elements.item(i)

                    if (dataNode !is Element) continue

                    val variables = dataNode.getElementsByTagName(BINDING_VARIABLE)
                    val imports = dataNode.getElementsByTagName(BINDING_IMPORT)

                    for (j in 0 until variables.length) {
                        val variable = variables.item(j)
                        val attributes = variable.attributes

                        mapVariables[attributes.getNamedItem("name").nodeValue] =
                            attributes.getNamedItem(BINDING_TYPE).nodeValue
                    }

                    for (j in 0 until imports.length) {
                        val importNode = imports.item(j)
                        val attributes = importNode.attributes

                        importList.add(attributes.getNamedItem(BINDING_TYPE).nodeValue)
                    }
                }
            }
        }

        val fileName = getBindingClassName(xmlFile)

        return UnitItemGenerator(
            className = fileName,
            classPackage = classPackage,
            dataBindingPackage = dataBindingPackage,
            layoutName = getLayoutName(xmlFile),
            variables = mapVariables,
            imports = importList
        )
    }

    private fun getDropDownUnitItemGenerator(
        xmlFile: File,
        bindingClasses: List<UnitItemGenerator>,
        classPackage: String,
        dataBindingPackage: String
    ): UnitItemGenerator? {
        val fileName = getBindingClassName(xmlFile)
        val layoutName = getLayoutName(xmlFile)

        val outBindingClass = bindingClasses.firstOrNull { item ->
            val requiredName = "${item.layoutName}$LAYOUT_NAME_DELIMITER$LAYOUT_DROPDOWN_SUFFIX"
            layoutName.contentEquals(requiredName)
        }

        println("layout $xmlFile out $outBindingClass")

        return outBindingClass?.let { outUnit ->
            DropDownUnitItemGenerator(
                className = fileName,
                classPackage = classPackage,
                dataBindingPackage = dataBindingPackage,
                layoutName = layoutName,
                outBindingClass = outUnit
            )
        }
    }

    companion object {
        private const val BINDING_LAYOUT = "layout"
        private const val LAYOUT_RES_PACKAGE = "layout"
        private const val BINDING_DATA = "data"
        private const val BINDING_TYPE = "type"
        private const val BINDING_VARIABLE = "variable"
        private const val BINDING_IMPORT = "import"
        private const val LAYOUT_NAME_DELIMITER = "_"
        private const val LAYOUT_DROPDOWN_SUFFIX = "dropdown"
    }
}
