/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.adapter

import dev.icerock.moko.units.UnitItem

interface Settable {
    fun set(value: List<UnitItem>)
}
