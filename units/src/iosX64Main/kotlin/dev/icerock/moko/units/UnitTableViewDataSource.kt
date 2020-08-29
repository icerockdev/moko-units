/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units

import kotlinx.cinterop.ExportObjCClass
import platform.Foundation.NSIndexPath
import platform.UIKit.UITableView
import platform.UIKit.UITableViewCell
import platform.UIKit.UITableViewDataSourceProtocol
import platform.UIKit.row
import platform.darwin.NSInteger
import platform.darwin.NSObject

@ExportObjCClass
actual class UnitTableViewDataSource internal actual constructor(
    private val tableView: UITableView,
    private val reloadDataHandler: UITableViewReloadHandler
) : NSObject(), UITableViewDataSourceProtocol {
    private val unitsRegistry = UnitsRegistry<UITableView, TableUnitItem>(tableView)
    actual var unitItems: List<TableUnitItem>? = null
        set(value) {
            val old = field
            field = value
            if (value != null) unitsRegistry.registerIfNeeded(value)
            reloadDataHandler(tableView, old, value)
        }

    init {
        tableView.dataSource = this
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun tableView(
        tableView: UITableView,
        cellForRowAtIndexPath: NSIndexPath
    ): UITableViewCell {
        val position = cellForRowAtIndexPath.row.toInt()
        val unitItem = unitItems?.get(position)
        requireNotNull(unitItem) { "cell can be requested only when unit exist" }

        val cell = tableView.dequeueReusableCellWithIdentifier(
            identifier = unitItem.reusableIdentifier,
            forIndexPath = cellForRowAtIndexPath
        )

        unitItem.bind(cell)
        return cell
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun tableView(
        tableView: UITableView,
        numberOfRowsInSection: NSInteger
    ): NSInteger {
        return unitItems?.size?.toLong() ?: 0
    }

    override fun numberOfSectionsInTableView(tableView: UITableView): NSInteger {
        return 1
    }
}
