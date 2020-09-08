/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units

import kotlinx.cinterop.ExportObjCClass
import platform.UIKit.UITableView
import platform.UIKit.UITableViewDataSourceProtocol
import platform.darwin.NSObject

typealias UITableViewReloadHandler = (UITableView, oldData: List<TableUnitItem>?, newData: List<TableUnitItem>?) -> Unit

@ExportObjCClass
expect class UnitTableViewDataSource internal constructor(
    tableView: UITableView,
    reloadDataHandler: UITableViewReloadHandler
) : NSObject, UITableViewDataSourceProtocol {
    var unitItems: List<TableUnitItem>?
}

fun createUnitTableViewDataSource(
    tableView: UITableView,
    reloadDataHandler: UITableViewReloadHandler = { _tableView, _, _ -> _tableView.reloadData() }
) = UnitTableViewDataSource(tableView, reloadDataHandler)
