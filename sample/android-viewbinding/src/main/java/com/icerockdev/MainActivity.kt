package com.icerockdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
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
                    return UnitSimpleDropdown().apply {
                        itemId = id
                        text = title
                        number = 9
                        obj1 = itemData
                        obj2 = itemData
                    }
                }

                override fun createComplexUnit(
                    id: Long,
                    title: String,
                    itemData: ItemData?
                ): UnitItem {
                    return UnitComplex().apply {
                        itemId = id
                        text = title
                        number = 9
                        obj1 = itemData
                        obj2 = itemData
                    }
                }

                override fun createBlueDividerUnit(id: Long): TableUnitItem {
                    return UnitBlueDivider().apply {
                        itemId = id
                        number = 9
                    }
                }

                override fun createRedDividerUnit(id: Long): TableUnitItem {
                    return UnitRedDivider().apply {
                        itemId = id
                        number = 9
                    }
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