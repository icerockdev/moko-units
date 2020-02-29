/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.basic.table

import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.units.TableUnitItem
import platform.UIKit.UITableView
import platform.UIKit.UITableViewCell
import platform.UIKit.UITableViewCellStyle
import platform.UIKit.hidden
import platform.UIKit.text

actual class BasicTableUnitItem actual constructor(
    override val itemId: Long,
    private val title: StringDesc,
    private val subtitle: StringDesc?,
    private val image: ImageResource?
) : TableUnitItem {
    override val reusableIdentifier: String = "BasicTableUnitItem"

    override fun bind(tableViewCell: UITableViewCell) {
        tableViewCell.text = title.localized()

        tableViewCell.detailTextLabel?.hidden = subtitle == null
        tableViewCell.detailTextLabel?.text = subtitle?.localized()

        tableViewCell.imageView?.image = image?.toUIImage()
        tableViewCell.imageView?.hidden = image == null
    }

    override fun register(intoView: UITableView) {
        intoView.registerClass(
            cellClass = SubtitleTableViewCell().`class`(),
            forCellReuseIdentifier = reusableIdentifier
        )
    }
}

private class SubtitleTableViewCell : UITableViewCell(
    style = UITableViewCellStyle.UITableViewCellStyleSubtitle,
    reuseIdentifier = null
)
