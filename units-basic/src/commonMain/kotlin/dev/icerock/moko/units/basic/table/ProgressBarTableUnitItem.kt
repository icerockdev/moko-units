/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.basic.table

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.units.TableUnitItem

expect class ProgressBarTableUnitItem(
    itemId: Long,
    progressBarColor: Color? = null
) : TableUnitItem
