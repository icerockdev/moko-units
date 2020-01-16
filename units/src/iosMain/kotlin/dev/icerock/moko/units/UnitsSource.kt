package dev.icerock.moko.units

import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.units.CollectionUnitItem
import platform.UIKit.UICollectionView
import platform.UIKit.UITableView

interface TableUnitsSource {
    var unitItems: List<TableUnitItem>?
}

interface CollectionUnitsSource {
    var unitItems: List<CollectionUnitItem>?
}

class UnitsDataSource private constructor() {
    companion object Factory {
        fun create(forCollectionView: UICollectionView): CollectionUnitsSource {
            val source = UnitCollectionViewDataSource(forCollectionView)
            return object: CollectionUnitsSource {
                override var unitItems = source.unitItems
            }
        }
        fun create(forTableView: UITableView): TableUnitsSource {
            val source = UnitTableViewDataSource(forTableView)
            return object: TableUnitsSource {
                override var unitItems = source.unitItems
            }
        }
    }
}