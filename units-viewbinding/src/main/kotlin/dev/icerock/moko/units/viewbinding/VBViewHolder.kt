/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.viewbinding

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class VBViewHolder<VB : ViewBinding>(
    val binding: VB,
    val lifecycleOwner: LifecycleOwner,
) : RecyclerView.ViewHolder(binding.root) {
    val context: Context get() = itemView.context
    var storedRef: Any? = null
}
