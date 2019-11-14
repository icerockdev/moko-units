/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units

import kotlinx.cinterop.ExportObjCClass
import platform.Foundation.NSIndexPath
import platform.UIKit.UICollectionView
import platform.UIKit.UICollectionViewCell
import platform.UIKit.UICollectionViewDataSourceProtocol
import platform.UIKit.row
import platform.darwin.NSInteger
import platform.darwin.NSObject

@ExportObjCClass
class UnitCollectionViewDataSource(
    private val collectionView: UICollectionView
) : NSObject(), UICollectionViewDataSourceProtocol {
    private val unitsRegistry = UnitsRegistry<UICollectionView, CollectionUnitItem>(collectionView)
    var unitItems: List<CollectionUnitItem>? = null
        set(value) {
            field = value
            if (value != null) unitsRegistry.registerIfNeeded(value)
            collectionView.reloadData()
        }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun collectionView(
        collectionView: UICollectionView,
        cellForItemAtIndexPath: NSIndexPath
    ): UICollectionViewCell {
        val position = cellForItemAtIndexPath.row.toInt()
        val unitItem = unitItems?.get(position)
        requireNotNull(unitItem) { "cell can be requested only when unit exist" }

        val cell = collectionView.dequeueReusableCellWithReuseIdentifier(
            identifier = unitItem.reusableIdentifier,
            forIndexPath = cellForItemAtIndexPath
        )

        unitItem.bind(cell)
        return cell
    }

    override fun collectionView(
        collectionView: UICollectionView,
        numberOfItemsInSection: NSInteger
    ): NSInteger {
        return unitItems?.size?.toLong() ?: 0
    }

    override fun numberOfSectionsInCollectionView(collectionView: UICollectionView): NSInteger {
        return 1
    }
}