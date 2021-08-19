/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.icerockdev.databinding.UnitComplexBinding
import dev.icerock.moko.units.viewbinding.VBTableUnitItem
import dev.icerock.moko.units.viewbinding.VBViewHolder

class UnitComplex(override val itemId: Long, val text: String) :
    VBTableUnitItem<UnitComplexBinding>() {
  
    override val layoutId: Int = R.layout.unit_complex

    override fun bindView(view: View): UnitComplexBinding {
        return UnitComplexBinding.bind(view)
    }

    override fun UnitComplexBinding.bindData(
      context: Context,
      lifecycleOwner: LifecycleOwner,
      viewHolder: VBViewHolder<UnitComplexBinding>
    ) {
        this.unitComplexTextView.text = text
        this.unitComplexTextView.setBackgroundColor(Color.parseColor("#FFC107"))
    }
}
