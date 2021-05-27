/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.library

import dev.icerock.moko.units.TableUnitItem

class Testing(val unitFactory: UnitFactory) {

    fun getUnits(): List<TableUnitItem> {
        return with(unitFactory) {
            listOf(
                createSimpleUnit(id = 1, title = "hello"),
                createSimpleUnit(id = 2, title = "world"),
                createSimpleUnit(id = 3, title = "and"),
                createSimpleUnit(id = 4, title = "work"),
                createSimpleUnit(id = 5, title = "!"),
                createSimpleUnit(id = 6, title = "diffable move"),
                createSimpleUnit(id = 7, title = "data", itemData = ItemData("there data"))
            )
        }
    }

    fun getDiffableUnits(): List<TableUnitItem> {
        return with(unitFactory) {
            listOf(
                createSimpleUnit(id = 1, title = "hello diff"),
                createSimpleUnit(id = 2, title = "world diff"),
                createSimpleUnit(id = 3, title = "and diff"),
                createSimpleUnit(id = 4, title = "work diff"),
                createSimpleUnit(id = 6, title = "diffable moved"),
                createSimpleUnit(id = 5, title = "! diff")
            )
        }
    }

    interface UnitFactory {
        fun createSimpleUnit(id: Long, title: String, itemData: ItemData? = null): TableUnitItem
    }
}
