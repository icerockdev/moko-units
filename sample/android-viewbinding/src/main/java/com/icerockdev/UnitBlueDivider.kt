/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.icerockdev.databinding.UnitBlueDividerBinding
import dev.icerock.moko.units.viewbinding.VBTableUnitItem
import dev.icerock.moko.units.viewbinding.VBViewHolder

class UnitBlueDivider(override val itemId: Long) :
    VBTableUnitItem<UnitBlueDividerBinding>() {

    override val layoutId: Int = R.layout.unit_blue_divider

    override fun bindView(view: View): UnitBlueDividerBinding {
        return UnitBlueDividerBinding.bind(view)
    }

    override fun UnitBlueDividerBinding.bindData(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        viewHolder: VBViewHolder<UnitBlueDividerBinding>
    ) {
        this.unitBlueDividerTextView.setBackgroundColor(Color.parseColor("#2196F3"))
    }
}
