/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import MultiPlatformLibrary
import MultiPlatformLibraryUnits
import UIKit

class DiffableTestViewController: UIViewController {

    @IBOutlet private var tableView: UITableView!
    private var dataSource: TableUnitsSource!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.tableFooterView = UIView()
        dataSource = TableUnitsSourceKt.diffable(for: tableView)
        dataSource.unitItems = Testing(unitFactory: self).getUnits()
    }
    
    @IBAction private func onSwitchChanged(_ sender: UISwitch) {
        print(sender.isOn)
        if sender.isOn {
            dataSource.unitItems = Testing(unitFactory: self).getDiffableUnits()
        } else {
            dataSource.unitItems = Testing(unitFactory: self).getUnits()
        }
    }
}

extension DiffableTestViewController: TestingUnitFactory {
    func createSimpleUnit(id: Int64, title: String, itemData: ItemData?) -> TableUnitItem {
        // without R.swift
        return UITableViewCellUnit<SimpleCell>(
            data: SimpleCell.CellModel(id: id, title: title),
            itemId: id,
            configurator: nil)
        // with R.swift
        //        return UITableViewCellUnit<SimpleCell>(
        //            data: SimpleCell.CellModel(id: id, title: title),
        //            itemId: id,
        //            reusable: R.nib.simpleCell,
        //            configurator: nil
        //        )
    }
}
