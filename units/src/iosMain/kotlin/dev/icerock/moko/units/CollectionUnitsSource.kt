/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */
package dev.icerock.moko.units

import platform.UIKit.UICollectionView

interface CollectionUnitsSource  {
    var unitItems: List<CollectionUnitItem>?
}

fun create(
    forCollectionView: UICollectionView,
    withReloadHandler: UICollectionViewReloadHandler = { collectionView, _, _ -> collectionView.reloadData() }
): CollectionUnitsSource {
    return object: CollectionUnitsSource {
        private val unitsSource = UnitCollectionViewDataSource.create(forCollectionView, withReloadHandler)
        override var unitItems: List<CollectionUnitItem>?
            get() {
                return unitsSource.unitItems
            }
            set(value) {
                unitsSource.unitItems = value
            }
    }
}