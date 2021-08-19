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
import kotlin.reflect.KClass

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
        lifecycleOwner: LifecycleOwner,
        isDropDownView: Boolean
    ): RecyclerView.ViewHolder {
        val unit = units[position]
        val viewHolderWrapper = convertView?.getTag(R.id.viewHolderId) as? ViewHolderWrapper

        return if (viewHolderWrapper != null &&
            viewHolderWrapper.unitType == unit::class &&
            viewHolderWrapper.isDropDownView == isDropDownView
        ) {
            viewHolderWrapper.viewHolder
        } else {
            val viewHolder =
                if (unit is DropDownUnitItem && isDropDownView) {
                    unit.createDropDownViewHolder(parent, lifecycleOwner)
                } else {
                    unit.createViewHolder(parent, lifecycleOwner)
                }
            val view = viewHolder.itemView
            view.setTag(
                R.id.viewHolderId,
                ViewHolderWrapper(viewHolder, unit::class, isDropDownView)
            )
            viewHolder
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder = getViewHolder(position, convertView, parent, lifecycleOwner, false)
        units[position].bindViewHolder(viewHolder)
        return viewHolder.itemView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val unit = units[position]
        return if (unit is DropDownUnitItem) {
            val viewHolder = getViewHolder(position, convertView, parent, lifecycleOwner, true)
            unit.bindDropDownViewHolder(viewHolder)
            viewHolder.itemView
        } else {
            getView(position, convertView, parent)
        }
    }

    private class ViewHolderWrapper(
        val viewHolder: RecyclerView.ViewHolder,
        val unitType: KClass<out UnitItem>,
        val isDropDownView: Boolean
    )
}
