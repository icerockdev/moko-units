/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit


open class UnitCollectionViewDataSource: NSObject, UICollectionViewDelegate, UICollectionViewDataSource {
  
  private var registeredIds: Set<String> = Set()
  
  @objc open func unit(from indexPath: IndexPath) -> UIAnyCellUnitProtocol? {
    return nil
  }
  
  open func setup(for collectionView: UICollectionView) {
    collectionView.dataSource = self
    collectionView.delegate = self
  }
  
  open func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
    return 0
  }
  
  open func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
    guard let unit = self.unit(from: indexPath) else { return UICollectionViewCell() }
    
    let reuseId = unit.reuseId
    if !registeredIds.contains(reuseId) {
      let nib = UINib(nibName: unit.nibName, bundle: unit.bundle)
      collectionView.register(nib, forCellWithReuseIdentifier: reuseId)
      registeredIds.insert(reuseId)
    }
    
    let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseId, for: indexPath)
    unit.fill(cell: cell)
    return cell
  }
  
  open func updateVisibleCells(in collectionView: UICollectionView) {
    for cell in collectionView.visibleCells {
      guard
        let indexPath = collectionView.indexPath(for: cell),
        let item = unit(from: indexPath) else {
          continue
      }
      item.fill(cell: cell)
    }
  }
}

