/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.icerockdev.databinding.UnitSimpleBinding
import dev.icerock.moko.units.viewbinding.VBTableUnitItem
import dev.icerock.moko.units.viewbinding.VBViewHolder

open class UnitSimple(override val itemId: Long, val text: String) :
    VBTableUnitItem<UnitSimpleBinding>() {

    override val layoutId: Int = R.layout.unit_simple

    override fun bindView(view: View): UnitSimpleBinding {
        return UnitSimpleBinding.bind(view)
    }

    override fun UnitSimpleBinding.bindData(
      context: Context,
      lifecycleOwner: LifecycleOwner,
      viewHolder: VBViewHolder<UnitSimpleBinding>,
    ) {
        this.unitSimpleTextView.text = text
    }
}
