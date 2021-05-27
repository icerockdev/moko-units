/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.library.mock

import com.icerockdev.library.ItemData
import com.icerockdev.library.Testing
import dev.icerock.moko.units.TableUnitItem

class TableFactory : Testing.UnitFactory {
    override fun createSimpleUnit(id: Long, title: String, itemData: ItemData?): TableUnitItem {
        return SimpleTableUnitMock(
            id = id,
            title = title,
            itemData = itemData
        )
    }

    override fun createBlueDividerUnit(id: Long): TableUnitItem {
        TODO("Not yet implemented")
    }

    override fun createComplexUnit(id: Long, title: String, itemData: ItemData?): TableUnitItem {
        TODO("Not yet implemented")
    }

    override fun createRedDividerUnit(id: Long): TableUnitItem {
        TODO("Not yet implemented")
    }
}


