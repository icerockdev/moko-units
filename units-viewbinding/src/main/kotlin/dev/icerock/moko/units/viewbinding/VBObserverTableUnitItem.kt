/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.viewbinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import dev.icerock.moko.units.TableUnitItem
import java.io.Closeable

abstract class VBObserverTableUnitItem<VB : ViewBinding> : TableUnitItem {

    @Suppress("UNCHECKED_CAST")
    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        with(viewHolder as VBViewHolder<VB>) {
            binding.bind(context, lifecycleOwner, closeable)
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val binding = inflate(view)
        return VBViewHolder(binding, lifecycleOwner)
    }

    abstract fun inflate(view: View): VB
    abstract fun VB.bind(context: Context, lifecycleOwner: LifecycleOwner, closeable: Closeable?)

    class VBViewHolder<VB : ViewBinding>(
        val binding: VB,
        val lifecycleOwner: LifecycleOwner
    ) : RecyclerView.ViewHolder(binding.root) {
        val context: Context get() = itemView.context
        var closeable: Closeable? = null
    }
}