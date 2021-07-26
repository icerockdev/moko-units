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
        return TableUnitsSourceKt.create(forTableView: tableView) { (view: UITableView, old: Array<TableUnitItem>?, new: Array<TableUnitItem>?) in
            
            if tableView.window == nil {
                /*
                 * tableView is not in view hierarchy here, therefore animation will not be performed and rows will not be reloaded,
                 * but data source has already been updated.
                 * reload data here prevent crash on next data source update, when calculated diff is empty, but is not actually empty.
                 */
                tableView.reloadData()
                return
            }
            
            let diff = (old ?? []).extendedDiff(
                new ?? [],
                isEqual: { compareTabelUnitItems(first: $0, second: $1) }
            )
            
            let update = BatchUpdate(diff: diff, indexPathTransform: { $0 })

            tableView.performBatchUpdates {
                tableView.apply(
                    diff,
                    deletionAnimation: deletionAnimation,
                    insertionAnimation: insertionAnimation,
                    indexPathTransform: { $0 }
                )
            } completion: { _ in
                let visibleIndexPaths: Array<IndexPath> = tableView.indexPathsForVisibleRows ?? []
                let cellsToUpdate: Array<IndexPath> = visibleIndexPaths.filter { indexPath in
                    let newItem: TableUnitItem? = new?.getSafe(indexPath: indexPath)
                    let noId: Bool = newItem?.itemId == TableUnitItemCompanion().NO_ID
                    let notInsertOrDelete: Bool = !update.contains(indexPath: indexPath)
                    return noId || notInsertOrDelete
                }
                let cellsForReload: Array<IndexPath> = cellsToUpdate.filter { indexPath in
                    let oldItem: TableUnitItem? = old?.getSafe(indexPath: indexPath)
                    let newItem: TableUnitItem? = new?.getSafe(indexPath: indexPath)
                    
                    if let oldItem = oldItem, let newItem = newItem,
                       type(of: oldItem) == type(of: newItem) {
                        return false
                    } else {
                        return true
                    }
                }
                cellsToUpdate.filter { !cellsForReload.contains($0) }.forEach { indexPath in
                    let newItem: TableUnitItem? = new?.getSafe(indexPath: indexPath)
                    let cell = tableView.cellForRow(at: indexPath)
                    if let newItem = newItem, let cell = cell {
                        newItem.bind(tableViewCell: cell)
                    }
                }

                tableView.reloadRows(at: cellsForReload ?? [], with: .none)
            }
        }
    }
    
    private static func compareTabelUnitItems(first: TableUnitItem, second: TableUnitItem) -> Bool {
        return first.itemId != TableUnitItemCompanion().NO_ID &&
            (type(of: first) == type(of: second)) &&
            (first.itemId == second.itemId)
    }
}

extension CollectionUnitsSourceKt {
    public static func diffable(for collectionView: UICollectionView) -> CollectionUnitsSource {
        return CollectionUnitsSourceKt.create(forCollectionView: collectionView) { (view, old, new) in
            
            if collectionView.window == nil {
                /*
                 * collectionView is not in view hierarchy here, therefore animation will not be performed and rows will not be reloaded,
                 * but data source has already been updated.
                 * reload data here prevent crash on next data source update, when calculated diff is empty, but is not actually empty.
                 */
                collectionView.reloadData()
                return
            }
            
            let diff = (old ?? []).extendedDiff(
                new ?? [],
                isEqual: { compareCollectionUnitItems(first: $0, second: $1) }
            )
            
            let update = BatchUpdate(diff: diff, indexPathTransform: { $0 })
            
            collectionView.performBatchUpdates {
                collectionView.apply(diff, updateData: { })
            } completion: { _ in
                let visibleIndexPaths: Array<IndexPath> = collectionView.indexPathsForVisibleItems ?? []
                let cellsToUpdate: Array<IndexPath> = visibleIndexPaths.filter { indexPath in
                    let newItem: CollectionUnitItem? = new?.getSafe(indexPath: indexPath)
                    let noId: Bool = newItem?.itemId == TableUnitItemCompanion().NO_ID
                    let notInsertOrDelete: Bool = !update.contains(indexPath: indexPath)
                    return noId || notInsertOrDelete
                }
                let cellsForReload: Array<IndexPath> = cellsToUpdate.filter { indexPath in
                    let oldItem: CollectionUnitItem? = old?.getSafe(indexPath: indexPath)
                    let newItem: CollectionUnitItem? = new?.getSafe(indexPath: indexPath)
                    
                    if let oldItem = oldItem, let newItem = newItem,
                       type(of: oldItem) == type(of: newItem) {
                        return false
                    } else {
                        return true
                    }
                }
                cellsToUpdate.filter { !cellsForReload.contains($0) }.forEach { indexPath in
                    let newItem: CollectionUnitItem? = new?.getSafe(indexPath: indexPath)
                    let cell = collectionView.cellForItem(at: indexPath)
                    if let newItem = newItem, let cell = cell {
                        newItem.bind(collectionViewCell: cell)
                    }
                }
                
                collectionView.reloadItems(at: collectionView.indexPathsForVisibleItems ?? [])
            }
        }
    }
    
    private static func compareCollectionUnitItems(first: CollectionUnitItem, second: CollectionUnitItem) -> Bool {
        return first.itemId != CollectionUnitItemCompanion().NO_ID &&
            (type(of: first) == type(of: second)) &&
            (first.itemId == second.itemId)
    }
}

fileprivate extension Array {
    func getSafe(indexPath: IndexPath) -> Element? {
        if indexPath.row < self.count {
            return self[indexPath.row]
        } else {
            return nil
        }
    }
}

fileprivate extension BatchUpdate {
    func contains(indexPath: IndexPath) -> Bool {
        return self.deletions.contains(indexPath) ||
            self.insertions.contains(indexPath)
    }
}
