/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units

import androidx.databinding.ViewDataBinding

actual interface UnitItem {
    val itemId: Long
    val layoutId: Int

    fun bind(viewDataBinding: ViewDataBinding)
}
