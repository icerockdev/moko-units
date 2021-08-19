/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.icerockdev.databinding.UnitRedDividerBinding
import dev.icerock.moko.units.DropDownUnitItem
import dev.icerock.moko.units.UnitItem
import dev.icerock.moko.units.viewbinding.VBTableUnitItem
import dev.icerock.moko.units.viewbinding.VBViewHolder

open class UnitRedDivider(override val itemId: Long) :
    VBTableUnitItem<UnitRedDividerBinding>(), UnitItem {

    override val layoutId: Int = R.layout.unit_red_divider
  
    override fun bindView(view: View): UnitRedDividerBinding {
        return UnitRedDividerBinding.bind(view)
    }

    override fun UnitRedDividerBinding.bindData(
      context: Context,
      lifecycleOwner: LifecycleOwner,
      viewHolder: VBViewHolder<UnitRedDividerBinding>
    ) {
        this.unitRedDividerTextView.setBackgroundColor(Color.parseColor("#F44336"))
    }
}
