/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.icerockdev.databinding.UnitBlueDividerBinding
import com.icerockdev.library.ItemData
import dev.icerock.moko.units.databinding.DataBindingUnitItem
import dev.icerock.moko.units.viewbinding.VBTableUnitItem
import dev.icerock.moko.units.viewbinding.VBViewHolder
import kotlin.Int
import kotlin.Long
import kotlin.String

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
