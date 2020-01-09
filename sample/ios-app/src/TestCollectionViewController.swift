/*
* Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
*/

import UIKit
import MultiPlatformLibrary
import MultiPlatformLibraryUnits

class TestCollectionViewController: UIViewController {
    
    @IBOutlet private var collectionView: UICollectionView!
    private var dataSource: UICollectionViewDataSource!

    override func viewDidLoad() {
        super.viewDidLoad()

        let testing = TestingCollection(unitFactory: self)

        let dataSource = FlatUnitCollectionViewDataSource()
        dataSource.setup(for: collectionView)

        let units = testing.getUnits() as! [UIAnyCellUnitProtocol]
        dataSource.units = units

        self.dataSource = dataSource
    }
}

extension TestCollectionViewController: TestingCollectionCollectionUnitFactory {
    func createSimpleUnit(id: Int64, title: String, itemData: ItemData?) -> CollectionUnitItem {
        // without R.swift
//        return UITableViewCellUnit<SimpleCell>(
//            data: SimpleCell.CellModel(id: id, title: title),
//            configurator: nil
//        )
        // with R.swift
        return UICollectionViewCellUnit<SimpleCollectionCell>(
            data: SimpleCollectionCell.CellModel(id: id, title: title),
            reusable: R.nib.simpleCollectionCell,
            configurator: nil
        )
    }
}
