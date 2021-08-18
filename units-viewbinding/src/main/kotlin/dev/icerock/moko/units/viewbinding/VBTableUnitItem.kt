/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.viewbinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import dev.icerock.moko.units.TableUnitItem
import java.io.Closeable

abstract class VBTableUnitItem<VB : ViewBinding> : TableUnitItem {

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        @Suppress("UNCHECKED_CAST")
        with(viewHolder as VBViewHolder<VB>) {
            binding.bindData(context, lifecycleOwner, this)
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val binding = bindView(view)
        return VBViewHolder(binding, lifecycleOwner)
    }
    
    @get:LayoutRes
    protected abstract val layoutId : Int
    protected abstract fun bindView(view: View): VB
    protected abstract fun VB.bindData(context: Context, lifecycleOwner: LifecycleOwner, viewHolder: VBViewHolder<VB>)

    override val viewType: Int
        get() = layoutId
    
    class VBViewHolder<VB : ViewBinding>(
        val binding: VB,
        val lifecycleOwner: LifecycleOwner
    ) : RecyclerView.ViewHolder(binding.root) {
        val context: Context get() = itemView.context
        // maybe change to `associatedObject: Any? = null` ?
        var closeable: Closeable? = null
    }
}
