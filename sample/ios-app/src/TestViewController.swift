/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit
import MultiPlatformLibrary
import MultiPlatformLibraryUnits

class TestViewController: UIViewController {
    
    @IBOutlet private var tableView: UITableView!
    private var dataSource: TableUnitsSource!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        dataSource = UnitsDataSource.Factory().create(forTableView: tableView)
        dataSource.unitItems = Testing(unitFactory: self).getUnits()
        tableView.tableFooterView = UIView()
    }
}

extension UITableViewCell: Reusable {
    public static func xibName() -> String { return String(describing: self) }
    public static func reusableIdentifier() -> String { return String(describing: self) }
}

extension TestViewController: TestingUnitFactory {
    func createSimpleUnit(id: Int64, title: String, itemData: ItemData?) -> TableUnitItem {
        // without R.swift
        return UITableViewCellUnit<SimpleCell>(
            data: SimpleCell.CellModel(id: id, title: itemData?.data ?? ""),
            itemId: id,
            configurator: nil)
        // with R.swift
//        return UITableViewCellUnit<SimpleCell>(
//            data: SimpleCell.CellModel(id: id, title: title),
//            reusable: R.nib.simpleCell,
//            configurator: nil
//        )
    }
}
