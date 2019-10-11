/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import dev.icerock.moko.units.DropDownUnitItem
import dev.icerock.moko.units.UnitItem

class UnitsAdapter(
    private val mLifecycleOwner: LifecycleOwner? = null
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var result = convertView
        val unit = units[position]

        val binding = getBinding(result, parent, unit.layoutId)

        if (binding != null) {
            if (mLifecycleOwner != null) {
                binding.lifecycleOwner = mLifecycleOwner
            }
            result = binding.root
            unit.bind(binding)
            binding.executePendingBindings()
        } else {
            throw IllegalStateException("Failed to get Binding for View with tag: ${result?.tag}")
        }

        return result
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var result = convertView
        val unit = units[position]

        if (unit is DropDownUnitItem) {
            val binding = getBinding(result, parent, unit.dropDownLayoutId)

            if (binding != null) {
                if (mLifecycleOwner != null) {
                    binding.lifecycleOwner = mLifecycleOwner
                }

                result = binding.root
                unit.bindDropDown(binding)
                binding.executePendingBindings()
            } else {
                throw IllegalStateException("Failed to get Binding for dropDown View with tag:${result?.tag}")
            }
            return result
        } else {
            return getView(position, result, parent)
        }
    }

    private fun getBinding(
        convertView: View?,
        parent: ViewGroup, @LayoutRes layoutId: Int
    ): ViewDataBinding? {
        return if (convertView == null) {
            val layoutInflater = LayoutInflater.from(parent.context)
            DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
        } else {
            DataBindingUtil.bind(convertView)
        }
    }
}
