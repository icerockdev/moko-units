/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.test

import dev.icerock.moko.units.CollectionUnitItem

@Suppress("EmptyDefaultConstructor")
expect abstract class CollectionUnitItemMock() : CollectionUnitItem {
    abstract val id: Long
}
