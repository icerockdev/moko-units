/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.test

import dev.icerock.moko.units.TableUnitItem

@Suppress("EmptyDefaultConstructor")
expect abstract class TableUnitItemMock() : TableUnitItem {
    abstract val id: Long
}
