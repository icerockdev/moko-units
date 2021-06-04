/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit

open class UICellUnit<Cell: Fillable> {
  
  public typealias ConfiguratorType = ((_ cell: Cell) -> Void)
  
  public var data: Cell.DataType
  @objc public var itemId: Int64
  public var reuseId: String
  public var nibName: String
  public var bundle: Bundle
  public var configurator: ConfiguratorType?
  public var onSelected: (() -> Void)?
  
  init(data: Cell.DataType,
       itemId: Int64,
       reuseId: String,
       nibName: String,
       bundle: Bundle = Bundle.main,
       configurator: ConfiguratorType? = nil) {
    self.data = data
    self.itemId = itemId
    self.reuseId = reuseId
    self.nibName = nibName
    self.bundle = bundle
    self.configurator = configurator
  }
  
  public convenience init(data: Cell.DataType,
                          itemId: Int64,
                          configurator: ConfiguratorType? = nil) {
    if let rType = (Cell.self as? Reusable.Type) {
      self.init(
        data: data,
        itemId: itemId,
        reuseId: rType.reusableIdentifier(),
        nibName: rType.xibName?() ?? rType.reusableIdentifier(),
        bundle: rType.bundle?() ?? Bundle.main,
        configurator: configurator
      )
    } else {
      self.init(
        data: data,
        itemId: itemId,
        reuseId: String(describing: Cell.self),
        nibName: String(describing: Cell.self),
        bundle: Bundle.main,
        configurator: configurator
      )
    }
  }
  
  public func itemData() -> Any {
    return data
  }
  
  internal func checkNibExistsInBundle() -> Bool {
    if (bundle.path(forResource: nibName, ofType: "nib") == nil) {
      NSLog("== MOKO-UNITS: Warning, nib \(nibName) not found in bundle \(bundle.bundlePath)")
      return false
    }
    return true
  }
}
