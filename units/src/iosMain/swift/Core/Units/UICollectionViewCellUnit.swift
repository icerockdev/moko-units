//
//  UICollectionViewCellUnit.swift
//  MultiPlatformLibraryUnits
//
//  Created by Andrey Tchernov on 16/01/2020.
//

import Foundation
import MultiPlatformLibrary

open class UICollectionViewCellUnit<Cell: Fillable & UICollectionViewCell>: UICellUnit<Cell>, CollectionUnitItem {
  
  public var reusableIdentifier: String {
    get {
      return reuseId
    }
  }
  
  public func register(intoView: Any?) {
    guard let collectionView = intoView as? UICollectionView else { return }
    collectionView.register(
      UINib(nibName: self.nibName, bundle: self.bundle),
      forCellWithReuseIdentifier: self.reusableIdentifier)
  }
  
  public func bind(cell: UICollectionViewCell) {
    guard let cell = cell as? Cell else { return }
    configurator?(cell)
    cell.fill(data)
  }
}
