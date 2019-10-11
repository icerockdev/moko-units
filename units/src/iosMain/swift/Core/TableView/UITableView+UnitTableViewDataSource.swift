/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit

public extension UITableViewCell {
  private func tableView() -> UITableView? {
    var superview = self.superview
    while superview != nil {
      if let tableView = superview as? UITableView {
        return tableView
      }
      superview = superview?.superview
    }
    return nil
  }
  
  public func indexPath() -> IndexPath? {
    guard let tableView = tableView() else { return nil }
    
    return tableView.indexPath(for: self)
  }
  
  public func itemData<ItemType>() -> ItemType? {
    guard let tableView = tableView() else { return nil }
    guard let dataSource = tableView.dataSource else { return nil }
    guard let indexPath = tableView.indexPath(for: self) else { return nil }
    guard let item = dataSource.perform(#selector(UnitTableViewDataSource.unit(from:)), with: indexPath).takeUnretainedValue() as? UITableViewCellUnitProtocol else { return nil }
    
    return item.itemData() as? ItemType
  }
}

public extension UITableView {
  public func itemData<ItemType>(at indexPath: IndexPath) -> ItemType? {
    guard let dataSource = self.dataSource else { return nil }
    guard let item = dataSource.perform(#selector(UnitTableViewDataSource.unit(from:)), with: indexPath).takeUnretainedValue() as? UITableViewCellUnitProtocol else { return nil }
    
    return item.itemData() as? ItemType
  }
  
  public func reloadUnitCell(at indexPath: IndexPath) {
    guard let cell = cellForRow(at: indexPath),
      let dataSource = self.dataSource,
      let unit = dataSource.perform(#selector(UnitTableViewDataSource.unit(from:)), with: indexPath).takeUnretainedValue() as? UITableViewCellUnitProtocol else {
        return
    }
    unit.fill(cell: cell)
  }
}

