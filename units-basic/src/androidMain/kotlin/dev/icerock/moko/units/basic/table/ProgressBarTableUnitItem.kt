/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.basic.table

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.units.basic.R

actual class ProgressBarTableUnitItem actual constructor(
    override val itemId: Long,
    private val progressBarColor: Color?
) : TableUnitItem {
    override val viewType: Int = R.layout.progress_bar_table_unit

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        viewHolder as ViewHolder

        // FIXME get default color from android theme
        val colorInt = progressBarColor?.run { argb.toInt() } ?: 0x00FF00FF
        DrawableCompat.setTint(viewHolder.progressBar.indeterminateDrawable, colorInt)
    }

    override fun createViewHolder(parent: ViewGroup, lifecycleOwner: LifecycleOwner): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.progress_bar_table_unit, parent, false)
        return ViewHolder(view)
    }

    private class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val progressBar: ProgressBar = view.findViewById(R.id.progress_circular)
    }
}
