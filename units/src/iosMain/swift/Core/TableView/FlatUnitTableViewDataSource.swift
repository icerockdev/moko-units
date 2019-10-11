/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit

open class FlatUnitTableViewDataSource: UnitTableViewDataSource {
  open var units:[UITableViewCellUnitProtocol] = []
  
  open override func unit(from indexPath: IndexPath) -> UITableViewCellUnitProtocol? {
    guard indexPath.row >= 0 && indexPath.row < units.count else { return nil }
    return units[indexPath.row]
  }
  
  open override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    return units.count
  }
}

