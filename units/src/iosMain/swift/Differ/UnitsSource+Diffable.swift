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
            
            guard let diff = old?.extendedDiff(
                new ?? [],
                isEqual: { compareTabelUnitItems(first: $0, second: $1) }
            ) else { return }
            
            if #available(iOS 11.0, *) {
                tableView.performBatchUpdates {
                    tableView.apply(
                        diff,
                        deletionAnimation: deletionAnimation,
                        insertionAnimation: insertionAnimation,
                        indexPathTransform: { $0 }
                    )
                } completion: { _ in
                    tableView.reloadRows(at: tableView.indexPathsForVisibleRows ?? [], with: .none)
                }
            } else {
                tableView.apply(
                    diff,
                    deletionAnimation: deletionAnimation,
                    insertionAnimation: insertionAnimation,
                    indexPathTransform: { $0 }
                )
                
                let update = BatchUpdate(diff: diff, indexPathTransform: { $0 })
                let cellsToUpdate = tableView.indexPathsForVisibleRows?.filter({ indexPath -> Bool in
                    !update.deletions.contains(indexPath) && !update.insertions.contains(indexPath)
                })
                
                tableView.reloadRows(at: cellsToUpdate ?? [], with: .none)
            }
        }
    }
    
    private static func compareTabelUnitItems(first: TableUnitItem, second: TableUnitItem) -> Bool {
        return (first.itemId == second.itemId)
    }
}

extension CollectionUnitsSourceKt {
    public static func diffable(for collectionView: UICollectionView) -> CollectionUnitsSource {
        return CollectionUnitsSourceKt.create(forCollectionView: collectionView) { (view, old, new) in
            
            guard let diff = old?.extendedDiff(
                new ?? [],
                isEqual: { compareCollectionUnitItems(first: $0, second: $1) }
            ) else { return }
            
            if #available(iOS 11.0, *) {
                collectionView.performBatchUpdates {
                    collectionView.apply(diff, updateData: { })
                } completion: { _ in
                    collectionView.reloadItems(at: collectionView.indexPathsForVisibleItems ?? [])
                }
            } else {
                collectionView.apply(diff, updateData: { })
                let update = BatchUpdate(diff: diff, indexPathTransform: { $0 })
                
                let cellsToUpdate = collectionView.indexPathsForVisibleItems.filter({ indexPath -> Bool in
                    !update.deletions.contains(indexPath) && !update.insertions.contains(indexPath)
                })
                
                collectionView.reloadItems(at: cellsToUpdate)
            }
        }
    }
    
    private static func compareCollectionUnitItems(first: CollectionUnitItem, second: CollectionUnitItem) -> Bool {
        return first.itemId == second.itemId
    }
}
