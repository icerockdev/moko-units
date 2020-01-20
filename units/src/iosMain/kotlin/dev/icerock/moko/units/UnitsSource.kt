package dev.icerock.moko.units

import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.units.CollectionUnitItem
import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.ExternalObjCClass
import kotlinx.cinterop.ObjCObjectMeta
import platform.UIKit.UICollectionView
import platform.UIKit.UITableView
import platform.UIKit.UICollectionViewDataSourceProtocol

interface TableUnitsSource {
    var unitItems: List<TableUnitItem>?
}

interface CollectionUnitsSource  {
    var unitItems: List<CollectionUnitItem>?
}

class UnitsDataSource private constructor() {
    companion object Factory {
        fun create(forCollectionView: UICollectionView): CollectionUnitsSource {
            return object: CollectionUnitsSource {
                private val unitsSource = UnitCollectionViewDataSource(forCollectionView)
                override var unitItems: List<CollectionUnitItem>?
                    get() {
                        return unitsSource.unitItems
                    }
                    set(value) {
                        unitsSource.unitItems = value
                    }
            }
        }
        fun create(forTableView: UITableView): TableUnitsSource {
            return object: TableUnitsSource {
                private val unitsSource = UnitTableViewDataSource(forTableView)
                override var unitItems: List<TableUnitItem>?
                    get() {
                        return unitsSource.unitItems
                    }
                    set(value) {
                        unitsSource.unitItems = value
                    }
            }
        }
    }
}