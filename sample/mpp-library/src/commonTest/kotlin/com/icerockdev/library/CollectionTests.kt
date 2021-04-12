/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.library

import com.icerockdev.library.mock.CollectionFactory
import com.icerockdev.library.mock.SimpleCollectionUnitMock
import dev.icerock.moko.units.CollectionUnitItem
import kotlin.test.Test
import kotlin.test.assertEquals

class CollectionTests {

    @Test
    fun `collection units building`() {
        val testing = TestingCollection(unitFactory = CollectionFactory())
        val units: List<CollectionUnitItem> = testing.getUnits()

        assertEquals(
            expected = listOf(
                SimpleCollectionUnitMock(
                    id = 1,
                    title = "hello",
                    itemData = null
                ),
                SimpleCollectionUnitMock(
                    id = 2,
                    title = "world",
                    itemData = null
                ),
                SimpleCollectionUnitMock(
                    id = 3,
                    title = "and",
                    itemData = null
                ),
                SimpleCollectionUnitMock(
                    id = 4,
                    title = "work",
                    itemData = null
                ),
                SimpleCollectionUnitMock(
                    id = 5,
                    title = "!",
                    itemData = null
                ),
                SimpleCollectionUnitMock(
                    id = 6,
                    title = "data",
                    itemData = ItemData("there data")
                )
            ),
            actual = units
        )
    }
}
