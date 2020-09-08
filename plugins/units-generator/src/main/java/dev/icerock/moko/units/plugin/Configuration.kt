/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.plugin

open class Configuration {
    lateinit var classesPackage: String
    lateinit var dataBindingPackage: String
    var layoutsSourceSet = "main"
}
