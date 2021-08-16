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
import dev.icerock.moko.units.TableUnitItem

abstract class VBTableUnitItem<VB> : TableUnitItem {
    @Suppress("UNCHECKED_CAST")
    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        bind(viewHolder.itemView.context, (viewHolder as VBViewHolder<VB>).binding)
    }

    override fun createViewHolder(
        parent: ViewGroup,
        lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder {
        val binding = inflate(LayoutInflater.from(parent.context), parent)
        return VBViewHolder(binding, binding.root())
    }

    abstract fun inflate(inflater: LayoutInflater, parent: ViewGroup): VB
    abstract fun VB.root(): View
    abstract fun bind(context: Context, binding: VB)

    class VBViewHolder<VB>(
        val binding: VB,
        root: View
    ) : RecyclerView.ViewHolder(root)
}
