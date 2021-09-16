/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.icerockdev.databinding.UnitSimpleDropdownBinding
import dev.icerock.moko.units.DropDownUnitItem
import dev.icerock.moko.units.viewbinding.VBDropDownUnitItem
import dev.icerock.moko.units.viewbinding.VBViewHolder

class UnitSimpleDropdown(
    private val text: String
) : VBDropDownUnitItem<UnitSimpleDropdownBinding>() {
    override val layoutId: Int
        get() = R.layout.unit_simple_dropdown

    override fun bindView(view: View): UnitSimpleDropdownBinding {
        return UnitSimpleDropdownBinding.bind(view)
    }

    override fun UnitSimpleDropdownBinding.bindData(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        viewHolder: VBViewHolder<UnitSimpleDropdownBinding>
    ) {
        unitSimpleDropdownTextView.text = text
    }

    class Combined(itemId: Long, text: String) : UnitSimple(itemId, text),
        DropDownUnitItem by UnitSimpleDropdown(text)
}
