/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units

import platform.UIKit.UITableView

interface TableUnitsSource {
    var unitItems: List<TableUnitItem>?
}

fun create(
    forTableView: UITableView,
    withReloadHandler: UITableViewReloadHandler
): TableUnitsSource {
    return object : TableUnitsSource {

        private val unitsSource = createUnitTableViewDataSource(forTableView, withReloadHandler)
        override var unitItems: List<TableUnitItem>?
            get() {
                return unitsSource.unitItems
            }
            set(value) {
                unitsSource.unitItems = value
            }
    }
}
