/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.library.mock

import com.icerockdev.library.ItemData
import com.icerockdev.library.TestingCollection
import dev.icerock.moko.units.CollectionUnitItem

class CollectionFactory : TestingCollection.CollectionUnitFactory {
    override fun createSimpleUnit(
        id: Long,
        title: String,
        itemData: ItemData?
    ): CollectionUnitItem {
        return SimpleCollectionUnitMock(
            id = id,
            title = title,
            itemData = itemData
        )
    }
}
