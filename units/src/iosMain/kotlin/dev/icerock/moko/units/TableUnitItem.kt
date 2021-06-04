/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units

import platform.UIKit.UITableView
import platform.UIKit.UITableViewCell

actual interface TableUnitItem: RegistryUnit<UITableView> {
    val itemId: Long

    override val reusableIdentifier: String

    override fun register(intoView: UITableView)

    fun bind(tableViewCell: UITableViewCell)

    actual companion object {
        actual val NO_ID: Long = -1
    }
}
