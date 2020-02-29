/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.basic.table

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.graphics.toUIColor
import dev.icerock.moko.units.TableUnitItem
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UITableView
import platform.UIKit.UITableViewCell
import platform.UIKit.UITableViewCellStyle
import platform.UIKit.addSubview
import platform.UIKit.centerXAnchor
import platform.UIKit.centerYAnchor
import platform.UIKit.tintColor
import platform.UIKit.translatesAutoresizingMaskIntoConstraints

actual class ProgressBarTableUnitItem actual constructor(
    override val itemId: Long,
    private val progressBarColor: Color?
) : TableUnitItem {
    override val reusableIdentifier: String = "ProgressBarTableUnitItem"

    override fun bind(tableViewCell: UITableViewCell) {
        tableViewCell as ProgressBarTableViewCell

        tableViewCell.activityIndicatorView.color = progressBarColor?.toUIColor() ?: tableViewCell.tintColor
    }

    override fun register(intoView: UITableView) {
        intoView.registerClass(
            cellClass = ProgressBarTableViewCell().`class`(),
            forCellReuseIdentifier = reusableIdentifier
        )
    }
}

private class ProgressBarTableViewCell : UITableViewCell(
    style = UITableViewCellStyle.UITableViewCellStyleDefault,
    reuseIdentifier = null
) {
    val activityIndicatorView: UIActivityIndicatorView by lazy {
        UIActivityIndicatorView().apply {
            translatesAutoresizingMaskIntoConstraints = false
        }
    }

    external override fun prepareForReuse() {
        super.prepareForReuse()

        activityIndicatorView.startAnimating()
    }

    init {
        contentView.addSubview(activityIndicatorView)

        activityIndicatorView.centerXAnchor.constraintEqualToAnchor(contentView.centerXAnchor).active = true
        activityIndicatorView.centerYAnchor.constraintEqualToAnchor(contentView.centerYAnchor).active = true
    }
}
