/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units

import platform.UIKit.UICollectionView
import platform.UIKit.UICollectionViewCell

actual interface CollectionUnitItem : RegistryUnit<UICollectionView> {
    val itemId: Long

    override val reusableIdentifier: String

    override fun register(intoView: UICollectionView)

    fun bind(collectionViewCell: UICollectionViewCell)

    actual companion object {
        actual val NO_ID: Long = -1
    }
}
