/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units

class UnitsRegistry<V, U : RegistryUnit<V>>(private val view: V) {
    private val registeredUnits = mutableSetOf<U>()

    fun registerIfNeeded(unitItem: U) {
        if (registeredUnits.contains(unitItem)) return

        registeredUnits.add(unitItem)
        unitItem.register(view)
    }

    fun registerIfNeeded(list: List<U>) {
        list.forEach { registerIfNeeded(it) }
    }
}