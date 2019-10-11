/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units

import androidx.databinding.ViewDataBinding

interface DropDownUnitItem {
    val dropDownLayoutId: Int

    fun bindDropDown(viewDataBinding: ViewDataBinding)
}
