/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.basic.table

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.graphics.toUIColor
import dev.icerock.moko.units.TableUnitItem
import kotlinx.cinterop.ExportObjCClass
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UIColor
import platform.UIKit.UITableView
import platform.UIKit.UITableViewCell
import platform.UIKit.UITableViewCellStyle
import platform.UIKit.addSubview
import platform.UIKit.bottomAnchor
import platform.UIKit.centerXAnchor
import platform.UIKit.topAnchor
import platform.UIKit.translatesAutoresizingMaskIntoConstraints

actual class ProgressBarTableUnitItem actual constructor(
    override val itemId: Long,
    private val progressBarColor: Color?
) : TableUnitItem {
    override val reusableIdentifier: String = "ProgressBarTableUnitItem"

    override fun bind(tableViewCell: UITableViewCell) {
        tableViewCell as ProgressBarTableViewCell

        tableViewCell.activityIndicatorView.color = progressBarColor?.toUIColor() ?: tableViewCell.defaultColor
    }

    override fun register(intoView: UITableView) {
        intoView.registerClass(
            cellClass = ProgressBarTableViewCell(
                style = UITableViewCellStyle.UITableViewCellStyleDefault,
                reuseIdentifier = null
            ).`class`(),
            forCellReuseIdentifier = reusableIdentifier
        )
    }
}

@ExportObjCClass
private class ProgressBarTableViewCell @OverrideInit constructor(
    style: UITableViewCellStyle,
    reuseIdentifier: String?
) : UITableViewCell(
    style = style,
    reuseIdentifier = reuseIdentifier
) {
    lateinit var defaultColor: UIColor
        private set
    val activityIndicatorView: UIActivityIndicatorView by lazy {
        UIActivityIndicatorView().apply {
            translatesAutoresizingMaskIntoConstraints = false
            startAnimating()
            defaultColor = color
        }
    }

    override fun prepareForReuse() {
        super.prepareForReuse()

        activityIndicatorView.startAnimating()
    }

    init {
        contentView.addSubview(activityIndicatorView)

        activityIndicatorView.centerXAnchor.constraintEqualToAnchor(
            anchor = contentView.centerXAnchor
        ).active = true
        activityIndicatorView.topAnchor.constraintEqualToAnchor(
            anchor = contentView.topAnchor,
            constant = 8.0
        ).active = true
        contentView.bottomAnchor.constraintEqualToAnchor(
            anchor = activityIndicatorView.bottomAnchor,
            constant = 8.0
        ).active = true
    }
}
