/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Differ
import MultiPlatformLibrary
import UIKit

extension TableUnitsSourceKt {
  public static func diffable(for tableView: UITableView) -> TableUnitsSource {
    return TableUnitsSourceKt.create(forTableView: tableView) { (view, old, new) in
      view.animateRowChanges(
        oldData: old ?? [],
        newData: new ?? [],
        isEqual: { $0.itemId == $1.itemId })
    }
  }
}

extension CollectionUnitsSourceKt {
  public static func diffable(for collectionView: UICollectionView) -> CollectionUnitsSource {
    return CollectionUnitsSourceKt.create(forCollectionView: collectionView) { (view, old, new) in
      view.animateItemChanges(
        oldData: old ?? [],
        newData: new ?? [],
        isEqual: { $0.itemId == $1.itemId },
        updateData: { })
    }
  }
}
