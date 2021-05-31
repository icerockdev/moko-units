/*
* Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
*/

import UIKit
import MultiPlatformLibrary
import MultiPlatformLibraryUnits

class TestCollectionViewController: UIViewController {
    
    @IBOutlet private var collectionView: UICollectionView!
    private var dataSource: CollectionUnitsSource!

    override func viewDidLoad() {
        super.viewDidLoad()
        dataSource = CollectionUnitsSourceKt.diffable(for: collectionView)
        dataSource.unitItems = TestingCollection(unitFactory: self).getUnits()
    }
    
    @IBAction private func onShuffleTouched(_ sender: UIButton!) {
        dataSource.unitItems = TestingCollection(unitFactory: self).getUnits().shuffled()
    }
}

extension TestCollectionViewController: TestingCollectionCollectionUnitFactory {
    func createSimpleUnit(id: Int64, title: String, itemData: ItemData?) -> CollectionUnitItem {
        // without R.swift
//        return UICollectionViewCellUnit<SimpleCollectionCell>(
//            data: SimpleCollectionCell.CellModel(id: id, title: title),
//            itemId: id,
//            configurator: nil
//        )
        // with R.swift
        return UICollectionViewCellUnit<SimpleCollectionCell>(
            data: SimpleCollectionCell.CellModel(id: id, title: title),
            itemId: id,
            reusable: R.nib.simpleCollectionCell,
            configurator: nil
        )
    }
}
