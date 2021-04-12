/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.test

import dev.icerock.moko.units.CollectionUnitItem
import platform.UIKit.UICollectionView
import platform.UIKit.UICollectionViewCell

@Suppress("EmptyDefaultConstructor")
actual abstract class CollectionUnitItemMock actual constructor() : CollectionUnitItem {
    actual abstract val id: Long

    override val itemId: Long get() = id

    override val reusableIdentifier: String
        get() = TODO("Not yet implemented")

    override fun register(intoView: UICollectionView) {
        TODO("Not yet implemented")
    }

    override fun bind(collectionViewCell: UICollectionViewCell) {
        TODO("Not yet implemented")
    }
}
