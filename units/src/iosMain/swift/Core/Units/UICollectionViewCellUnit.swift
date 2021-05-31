/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit
import MultiPlatformLibrary

open class UICollectionViewCellUnit<Cell: Fillable & UICollectionViewCell>: UICellUnit<Cell>, CollectionUnitItem {
  
  public var reusableIdentifier: String {
    get {
      return reuseId
    }
  }
  
  public func register(intoView: Any?) {
    guard let collectionView = intoView as? UICollectionView else { return }
    if (checkNibExistsInBundle()) {
        collectionView.register(
          UINib(nibName: self.nibName, bundle: self.bundle),
            forCellWithReuseIdentifier: self.reusableIdentifier
        )
    } else {
        collectionView.register(Cell.self, forCellWithReuseIdentifier: reusableIdentifier)
    }
  }
  
  public func bind(collectionViewCell: UICollectionViewCell) {
    guard let cell = collectionViewCell as? Cell else { return }
    configurator?(cell)
    cell.fill(data)
  }
}
