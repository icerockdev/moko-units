/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.test

import dev.icerock.moko.units.TableUnitItem
import platform.UIKit.UITableView
import platform.UIKit.UITableViewCell

@Suppress("EmptyDefaultConstructor")
actual abstract class TableUnitItemMock actual constructor() : TableUnitItem {

    actual abstract val id: Long

    override val itemId: Long get() = id

    override val reusableIdentifier: String
        get() = TODO("Not yet implemented")

    override fun bind(tableViewCell: UITableViewCell) {
        TODO("Not yet implemented")
    }

    override fun register(intoView: UITableView) {
        TODO("Not yet implemented")
    }
}
