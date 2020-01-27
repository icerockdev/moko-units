/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.adapter

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.icerock.moko.units.UnitItem

private typealias ViewHolderFactory = (
    parent: ViewGroup,
    lifecycleOwner: LifecycleOwner
) -> RecyclerView.ViewHolder

class UnitsRecyclerViewAdapter(
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Settable {

    var units: List<UnitItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val viewHolderFactory = mutableMapOf<Int, ViewHolderFactory>()

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
        val viewType = units[position].viewType
        if (!viewHolderFactory.containsKey(viewType)) {
            viewHolderFactory[viewType] = units[position]::createViewHolder
        }
        return viewType
    }

    override fun getItemId(position: Int): Long {
        return units[position].itemId
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val factory = requireNotNull(viewHolderFactory[viewType]) {
            "viewHolderFactory must be set at getItemViewType"
        }

        return factory(parent, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val unit = units[position]
        unit.bindViewHolder(holder)
    }
}
