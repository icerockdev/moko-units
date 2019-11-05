/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.icerock.moko.units.DropDownUnitItem
import dev.icerock.moko.units.R
import dev.icerock.moko.units.UnitItem

class UnitsAdapter(
    private val lifecycleOwner: LifecycleOwner
) : BaseAdapter(), Settable {

    var units: List<UnitItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun set(value: List<UnitItem>) {
        units = value
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getCount(): Int {
        return units.size
    }

    override fun getItem(position: Int): UnitItem {
        return units[position]
    }

    override fun getItemId(position: Int): Long {
        return units[position].itemId
    }

    private fun getViewHolder(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
        lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder {
        val existViewHolder = convertView?.getTag(R.id.viewHolderId) as? RecyclerView.ViewHolder
        return if (existViewHolder != null) existViewHolder
        else {
            val unit = units[position]
            val viewHolder =
                if (unit is DropDownUnitItem) unit.createDropDownViewHolder(parent, lifecycleOwner)
                else unit.createViewHolder(parent, lifecycleOwner)
            val view = viewHolder.itemView
            view.setTag(R.id.viewHolderId, viewHolder)
            viewHolder
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder = getViewHolder(position, convertView, parent, lifecycleOwner)
        units[position].bindViewHolder(viewHolder)
        return viewHolder.itemView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val unit = units[position]
        return if (unit is DropDownUnitItem) {
            val viewHolder = getViewHolder(position, convertView, parent, lifecycleOwner)
            unit.bindDropDownViewHolder(viewHolder)
            viewHolder.itemView
        } else {
            getView(position, convertView, parent)
        }
    }
}
