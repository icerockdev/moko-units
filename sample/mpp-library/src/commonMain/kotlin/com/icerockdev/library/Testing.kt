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
                createRedDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 2, title = "world"),
                createRedDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 3, title = "and"),
                createRedDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 4, title = "work"),
                createRedDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 5, title = "!"),
                createRedDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 6, title = "diffable move"),
                createRedDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 7, title = "data", itemData = ItemData("there data"))
            )
        }
    }

    fun getDiffableUnits(): List<TableUnitItem> {
        return with(unitFactory) {
            listOf(
                createComplexUnit(id = 1, title = "hello diff"),
                createRedDividerUnit(id = TableUnitItem.NO_ID),
                createComplexUnit(id = 20, title = "world diff"),
                createBlueDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 30, title = "and diff"),
                createRedDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 4, title = "work diff"),
                createBlueDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 6, title = "diffable moved"),
                createRedDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 5, title = "! diff"),
                createBlueDividerUnit(id = TableUnitItem.NO_ID),
                createSimpleUnit(id = 7, title = "data", itemData = ItemData("there data"))
            )
        }
    }

    interface UnitFactory {
        fun createSimpleUnit(id: Long, title: String, itemData: ItemData? = null): TableUnitItem
        fun createComplexUnit(id: Long, title: String, itemData: ItemData? = null): TableUnitItem
        fun createRedDividerUnit(id: Long): TableUnitItem
        fun createBlueDividerUnit(id: Long): TableUnitItem
    }
}
