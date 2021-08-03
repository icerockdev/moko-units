/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units

import kotlinx.cinterop.ExportObjCClass
import platform.UIKit.UICollectionView
import platform.UIKit.UICollectionViewDataSourceProtocol
import platform.darwin.NSObject

typealias UICollectionViewReloadHandler = (
    UICollectionView,
    oldData: List<CollectionUnitItem>?,
    newData: List<CollectionUnitItem>?
) -> Unit

@ExportObjCClass
expect class UnitCollectionViewDataSource internal constructor(
    collectionView: UICollectionView,
    reloadDataHandler: UICollectionViewReloadHandler
) : NSObject, UICollectionViewDataSourceProtocol {

    var unitItems: List<CollectionUnitItem>?
}

fun createUnitCollectionViewDataSource(
    collectionView: UICollectionView,
    reloadDataHandler: UICollectionViewReloadHandler = { _collectionView, _, _ -> _collectionView.reloadData() }
) = UnitCollectionViewDataSource(collectionView, reloadDataHandler)
