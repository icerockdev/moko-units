/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.icerockdev.library.ItemData
import com.icerockdev.library.Testing
import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.units.UnitItem
import dev.icerock.moko.units.adapter.UnitsAdapter
import dev.icerock.moko.units.adapter.UnitsRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val test = Testing(
            unitFactory = object : Testing.UnitFactory {
                override fun createSimpleUnit(
                    id: Long,
                    title: String,
                    itemData: ItemData?
                ): UnitItem {
                    return UnitSimpleDropdown.Combined(
                        itemId = id,
                        text = title
                    )
                }

                override fun createComplexUnit(
                    id: Long,
                    title: String,
                    itemData: ItemData?
                ): UnitItem {
                    return UnitComplex(
                        itemId = id,
                        text = title
                    )
                }

                override fun createBlueDividerUnit(id: Long): TableUnitItem {
                    return UnitBlueDivider(
                        itemId = id
                    )
                }

                override fun createRedDividerUnit(id: Long): TableUnitItem {
                    return UnitRedDivider(
                        itemId = id
                    )
                }
            }
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val spinner: AppCompatSpinner = findViewById(R.id.spinner)
        val switch: Switch = findViewById(R.id.switch1)

        switch.setOnCheckedChangeListener { _, isChecked ->
            (recyclerView.adapter as? UnitsRecyclerViewAdapter)?.units = if (isChecked) {
                test.getDiffableUnits()
            } else {
                test.getUnits()
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = UnitsRecyclerViewAdapter(this).apply {
            units = test.getUnits()
        }

        spinner.adapter = UnitsAdapter(this).apply {
            units = test.getUnits()
        }
    }
}