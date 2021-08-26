/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.databinding

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.icerock.moko.units.UnitItem

interface DataBindingUnitItem : UnitItem {
    val layoutId: Int

    fun bind(viewDataBinding: ViewDataBinding)

    override val viewType: Int get() = layoutId

    override fun createViewHolder(
        parent: ViewGroup,
        lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder {
        return DataBindingUnitViewHolder<ViewDataBinding>(parent, layoutId).apply {
            binding.lifecycleOwner = lifecycleOwner
        }
    }

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val unitViewHolder = viewHolder as DataBindingUnitViewHolder<ViewDataBinding>
        val binding = unitViewHolder.binding
        bind(binding)
        binding.executePendingBindings()
    }
}
