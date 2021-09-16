/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.library

import com.icerockdev.library.mock.SimpleTableUnitMock
import com.icerockdev.library.mock.TableFactory
import dev.icerock.moko.units.TableUnitItem
import kotlin.test.Test
import kotlin.test.assertEquals

class TableTests {

    @Test
    fun `table units building`() {
        val testing = Testing(unitFactory = TableFactory())
        val units: List<TableUnitItem> = testing.getUnits()

        assertEquals(
            expected = listOf(
                SimpleTableUnitMock(
                    id = 1,
                    title = "hello",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = -1,
                    title = "",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = 2,
                    title = "world",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = -1,
                    title = "",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = 3,
                    title = "and",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = -1,
                    title = "",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = 4,
                    title = "work",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = -1,
                    title = "",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = 5,
                    title = "!",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = -1,
                    title = "",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = 6,
                    title = "diffable move",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = -1,
                    title = "",
                    itemData = null
                ),
                SimpleTableUnitMock(
                    id = 7,
                    title = "data",
                    itemData = ItemData("there data")
                )
            ),
            actual = units
        )
    }
}
