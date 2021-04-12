/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.test

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import dev.icerock.moko.units.TableUnitItem

@Suppress("EmptyDefaultConstructor")
actual abstract class TableUnitItemMock actual constructor() : TableUnitItem {

    actual abstract val id: Long

    override val itemId: Long get() = id

    override val viewType: Int
        get() = TODO("Not yet implemented")

    override fun bindViewHolder(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder) {
        TODO("Not yet implemented")
    }

    override fun createViewHolder(
        parent: ViewGroup,
        lifecycleOwner: LifecycleOwner
    ): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }
}
