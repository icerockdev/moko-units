/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingUnitViewHolder<T : ViewDataBinding>(
    val binding: T
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
        @LayoutRes mLayout: Int
    ) : this(
        binding = DataBindingUtil.inflate<T>(
            LayoutInflater.from(parent.context),
            mLayout,
            parent,
            false
        )
    )
}
