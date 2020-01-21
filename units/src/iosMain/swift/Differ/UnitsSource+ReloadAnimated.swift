/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Differ
import MultiPlatformLibrary
import UIKit

extension TableUnitsSource {
  public func reloadItemsAnimated(_ items: [TableUnitItem]) {
    let oldItems = unitItems ?? []
    self.unitItems = items
    self.tableView?.animateRowChanges(
      oldData: oldItems,
      newData: items,
      isEqual: { $0.itemId == $1.itemId
    })
  }
}

extension CollectionUnitsSource {
  public func reloadItemsAnimated(_ items: [CollectionUnitItem]) {
    let oldItems = unitItems ?? []
    self.collectionView?.animateItemChanges(
      oldData: oldItems,
      newData: items,
      isEqual: { $0.itemId == $1.itemId },
      updateData: { [weak self] in
        self?.unitItems = items
    })
  }
}
