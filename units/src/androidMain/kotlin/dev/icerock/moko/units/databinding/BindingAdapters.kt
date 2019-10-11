/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.databinding

import android.widget.Adapter
import android.widget.AdapterView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.icerock.moko.units.UnitItem
import dev.icerock.moko.units.adapter.Settable
import dev.icerock.moko.units.adapter.UnitsRecyclerViewAdapter

@BindingAdapter(value = ["bindValue", "initFromLast"], requireAll = false)
fun RecyclerView.setItems(units: List<UnitItem>?, initFromLast: Boolean?) {
    val oldCount = adapter?.itemCount
    adapter?.setList(units.orEmpty())

    if (units == null) return
    if (oldCount == null) return

    if (initFromLast != true) return
    if (oldCount != 0) return

    val layout = layoutManager
    if (layout is LinearLayoutManager) {
        layout.scrollToPositionWithOffset(units.lastIndex, 0)
    } else {
        layout?.scrollToPosition(units.lastIndex)
    }
}

@BindingAdapter(value = ["bindValue", "skeletonValues"], requireAll = false)
fun RecyclerView.setItems(value: List<UnitItem>?, skeletonValues: List<UnitItem>?) {
    if (value == null) {
        adapter?.setList(skeletonValues.orEmpty())
    } else {
        adapter?.setList(value)
    }
}

@BindingAdapter("adapter")
fun RecyclerView.setAdapter(adapterClassName: String) {
    val adapterClass = this.javaClass.classLoader?.loadClass(adapterClassName)

    if (adapterClass == UnitsRecyclerViewAdapter::class.java) {
        adapter = UnitsRecyclerViewAdapter(context as? LifecycleOwner)
        return
    }

    val newAdapter = adapterClass?.newInstance() as? RecyclerView.Adapter<*>
    if (newAdapter != null && adapter == null) {
        adapter = newAdapter
    }
}

@BindingAdapter("decorators")
fun RecyclerView.setDecorator(decorators: List<RecyclerView.ItemDecoration>) {
    decorators.forEach {
        addItemDecoration(it)
    }
}

@BindingAdapter("bindValue")
fun AdapterView<Adapter>.setItems(value: List<UnitItem>?) {
    adapter.setList(value.orEmpty())
}

@BindingAdapter("adapter")
fun AdapterView<Adapter>.setItemsWithAdapter(adapterClassName: String) {
    val adapterClass = this.javaClass.classLoader?.loadClass(adapterClassName)
    val newAdapter = adapterClass?.newInstance() as? Adapter
    if (newAdapter != null && adapter == null) {
        adapter = newAdapter
    }
}

private fun Any.setList(list: List<UnitItem>) {
    if (this is Settable) {
        this.set(list)
    } else {
        throw IllegalArgumentException("can't set list on $this (not implemented Settable interface)")
    }
}
