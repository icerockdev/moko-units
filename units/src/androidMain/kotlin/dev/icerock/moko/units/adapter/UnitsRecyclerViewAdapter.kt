/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.icerock.moko.units.UnitItem

class UnitsRecyclerViewAdapter(
    private val mLifecycleOwner: LifecycleOwner? = null
) : RecyclerView.Adapter<UnitViewHolder<ViewDataBinding>>(), Settable {

    var units: List<UnitItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        setHasStableIds(true)
    }

    override fun set(value: List<UnitItem>) {
        units = value
    }

    override fun getItemCount(): Int {
        return units.size
    }

    override fun getItemViewType(position: Int): Int {
        return units[position].layoutId
    }

    override fun getItemId(position: Int): Long {
        return units[position].itemId
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UnitViewHolder<ViewDataBinding> {
        val viewHolder = UnitViewHolder<ViewDataBinding>(parent, viewType)

        if (mLifecycleOwner != null) {
            viewHolder.binding.lifecycleOwner = mLifecycleOwner
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: UnitViewHolder<ViewDataBinding>, position: Int) {
        val unit = units[position]

        with(holder.binding) {
            unit.bind(this)
            executePendingBindings()
        }
    }
}
