/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit

open class FlatUnitCollectionViewDataSource: UnitCollectionViewDataSource {
  public var units:[UIAnyCellUnitProtocol] = []
  
  open override func unit(from indexPath: IndexPath) -> UIAnyCellUnitProtocol? {
    guard indexPath.row >= 0 && indexPath.row < units.count else { return nil }
    return units[indexPath.row]
  }
  
  open override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
    return units.count
  }
}

