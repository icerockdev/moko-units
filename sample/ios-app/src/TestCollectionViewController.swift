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
        dataSource = UnitsDataSource.Factory().create(forCollectionView: collectionView)
        dataSource.unitItems = TestingCollection(unitFactory: self).getUnits()
        collectionView.reloadData()
    }
}

extension UICollectionViewCell: Reusable {
    public static func xibName() -> String { return String(describing: self) }
    public static func reusableIdentifier() -> String { return String(describing: self) }
}

//
extension TestCollectionViewController: TestingCollectionCollectionUnitFactory {
    func createSimpleUnit(id: Int64, title: String, itemData: ItemData?) -> CollectionUnitItem {
        // without R.swift
        return UICollectionViewCellUnit<SimpleCollectionCell>(
            data: SimpleCollectionCell.CellModel(id: id, title: title),
            itemId: id,
            configurator: nil
        )
        // with R.swift
//        return UICollectionViewCellUnit<SimpleCollectionCell>(
//            data: SimpleCollectionCell.CellModel(id: id, title: title),
//            reusable: R.nib.simpleCollectionCell,
//            configurator: nil
//        )
    }
}
