/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.library.mock

import com.icerockdev.library.ItemData
import dev.icerock.moko.units.test.CollectionUnitItemMock

data class SimpleCollectionUnitMock(
    override val id: Long,
    val title: String,
    val itemData: ItemData?
) : CollectionUnitItemMock()
