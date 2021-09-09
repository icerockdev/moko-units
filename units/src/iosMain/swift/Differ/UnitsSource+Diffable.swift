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
            
            // traces for calculating previous indexPaths
            let outputDiffPathTraces = (old ?? []).outputDiffPathTraces(
                to: new ?? [],
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
                    return !update.insertions.contains(indexPath)
                }
                let cellsForReload: Array<IndexPath> = cellsToUpdate.filter { indexPath in
                    guard let oldItemIndexPath = indexPath.toOldIndexPath(traces: outputDiffPathTraces) else {
                        return true
                    }
                    let oldItem: TableUnitItem? = old?.getSafe(indexPath: oldItemIndexPath)
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
            
            // traces for calculating previous indexPaths
            let outputDiffPathTraces = (old ?? []).outputDiffPathTraces(
                to: new ?? [],
                isEqual: { compareCollectionUnitItems(first: $0, second: $1) }
            )
            
            let update = BatchUpdate(diff: diff, indexPathTransform: { $0 })
            
            collectionView.performBatchUpdates {
                collectionView.apply(diff, updateData: { })
            } completion: { _ in
                let visibleIndexPaths: Array<IndexPath> = collectionView.indexPathsForVisibleItems ?? []
                let cellsToUpdate: Array<IndexPath> = visibleIndexPaths.filter { indexPath in
                    return !update.insertions.contains(indexPath)
                }
                let cellsForReload: Array<IndexPath> = cellsToUpdate.filter { indexPath in
                    guard let oldItemIndexPath = indexPath.toOldIndexPath(traces: outputDiffPathTraces) else {
                        return true
                    }
                    let oldItem: CollectionUnitItem? = old?.getSafe(indexPath: oldItemIndexPath)
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

fileprivate extension IndexPath {
    
    // Using new indexPath and diff (traces array), calculate index of cell that was before applying the diff, for preventing reload cells once again.
    func toOldIndexPath(traces: [Trace]) -> IndexPath? {
        
        // filter for only matchedTraces,
        // can't use type() function from here https://github.com/wokalski/Diff.swift/blob/61edde253f8b74ab475dfc0dd40941efacdff71d/Sources/Diff.swift#L97
        // because it is internal
        let matchedTraces = traces.filter { trace in
            // Trace is a vector with coords (from, to) that shows changes of cell position
            // if trace is a horizontal vector, it means row is deleted
            // if trace is vertical vector, it means row is inserted
            // we only need diagonal vectors, it means that row wasn't deleted or inserted
            trace.from.x + 1 == trace.to.x && trace.from.y + 1 == trace.to.y
        }
        
        // trace.from.y - current row index
        guard let trace = (matchedTraces.first { $0.from.y == self.row }) else {
            return nil
        }
        
        // trace.from.x - old row index
        return IndexPath(row: trace.from.x, section: 0)
    }
}
