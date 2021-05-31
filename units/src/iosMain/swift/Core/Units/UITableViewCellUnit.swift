/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit
import MultiPlatformLibrary

open class UITableViewCellUnit<Cell: Fillable & UITableViewCell>: UICellUnit<Cell>, TableUnitItem {
  
  public var reusableIdentifier: String {
    get {
      return reuseId
    }
  }
  
  public func register(intoView: Any?) {
    guard let tableView = intoView as? UITableView else { return }
    if (checkNibExistsInBundle()) {
        tableView.register(
          UINib(nibName: self.nibName, bundle: self.bundle),
          forCellReuseIdentifier: self.reusableIdentifier
        )
    } else {
        tableView.register(Cell.self, forCellReuseIdentifier: self.reusableIdentifier)
    }
  }
  
  public func bind(tableViewCell: UITableViewCell) {
    guard let cell = tableViewCell as? Cell else { return }
    configurator?(cell)
    cell.fill(data)
  }
}
