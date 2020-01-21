/*
* Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
*/


import UIKit
import MultiPlatformLibrary

extension TableUnitsSource {
  public func reloadItems(_ items: [TableUnitItem]) {
    self.unitItems = items
    self.tableView?.reloadData()
  }
}

extension CollectionUnitsSource {
  public func reloadItems(_ items: [CollectionUnitItem]) {
    self.unitItems = items
    self.collectionView?.reloadData()
  }
}
