/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Differ
import MultiPlatformLibrary
import UIKit

extension TableUnitsSourceKt {
  public static func diffable(
    for tableView: UITableView,
    deletionAnimation: DiffRowAnimation = .automatic,
    insertionAnimation: DiffRowAnimation = .automatic) -> TableUnitsSource {
    return TableUnitsSourceKt.create(forTableView: tableView) { (view, old, new) in
      view.animateRowChanges(
        oldData: old ?? [],
        newData: new ?? [],
        isEqual: { compareTabelUnitItems(first: $0, second: $1) },
        deletionAnimation: deletionAnimation,
        insertionAnimation: insertionAnimation)
        
        guard let diff = old?.extendedDiff(new ?? [], isEqual: { compareTabelUnitItems(first: $0, second: $1) }) else { return }
        let update = BatchUpdate(diff: diff, indexPathTransform: { $0 })
        let visibleRows = tableView.indexPathsForVisibleRows ?? []
        let filteredRows = visibleRows.filter { (indexPath) -> Bool in
            !update.insertions.contains(indexPath) && !update.deletions.contains(indexPath)
        }
        // reload rows, that wasn't added or deleted
        tableView.reloadRows(at: filteredRows, with: insertionAnimation)
    }
  }
    
    private static func compareTabelUnitItems(first: TableUnitItem, second: TableUnitItem) -> Bool {
        return first.itemId == second.itemId
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
