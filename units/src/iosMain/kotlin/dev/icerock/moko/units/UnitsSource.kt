package dev.icerock.moko.units

import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.units.CollectionUnitItem

interface TableUnitsSource {
    var unitItems: List<TableUnitItem>?
}

interface CollectionUnitsSource {
    var unitItems: List<CollectionUnitItem>?
}