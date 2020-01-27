/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.databinding

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.icerock.moko.units.DropDownUnitItem

interface DataBindingDropDownUnitItem : DropDownUnitItem {
    val dropDownLayoutId: Int

    fun bindDropDown(viewDataBinding: ViewDataBinding)

    override fun createDropDownViewHolder(
        parent: ViewGroup,
        lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder {
        return DataBindingUnitViewHolder<ViewDataBinding>(
            parent,
            dropDownLayoutId
        ).apply {
            binding.lifecycleOwner = lifecycleOwner
        }
    }

    override fun bindDropDownViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val unitViewHolder = viewHolder as DataBindingUnitViewHolder<ViewDataBinding>
        val binding = unitViewHolder.binding
        bindDropDown(binding)
        binding.executePendingBindings()
    }
}
