/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.viewbinding

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.units.UnitItem
import dev.icerock.moko.units.adapter.UnitsRecyclerViewAdapter

fun RecyclerView.bindUnits(lifecycleOwner: LifecycleOwner, units: LiveData<List<UnitItem>>) {
    val unitsAdapter = UnitsRecyclerViewAdapter(lifecycleOwner)
    adapter = unitsAdapter

    units.observe(lifecycleOwner) {
        if (units.value?.isNotEmpty() == true) {
            unitsAdapter.units = it.orEmpty()
        }
    }
}

fun RecyclerView.bindPagingUnits(
    lifecycleOwner: LifecycleOwner,
    units: LiveData<List<TableUnitItem>?>,
    nextPageAction: () -> Unit
) {
    val unitsAdapter = UnitsRecyclerViewAdapter(lifecycleOwner)
    layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    adapter = unitsAdapter

    units.observe(lifecycleOwner) {
        unitsAdapter.units = it.orEmpty()
    }

    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            val count = unitsAdapter.itemCount
            val position = getChildAdapterPosition(view)
            if (position != count - 1) return
            nextPageAction.invoke()
        }

        @Suppress("EmptyFunctionBlock")
        override fun onChildViewDetachedFromWindow(view: View) {}
    })
}
