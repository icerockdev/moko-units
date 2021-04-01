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
  
  public func itemData() -> Any {
    return data
  }
}

public extension UICellUnit where Cell: Reusable {
  convenience init(data: Cell.DataType,
  itemId: Int64,
  configurator: ConfiguratorType? = nil) {
    self.init(data: data,
              itemId: itemId,
              reuseId: Cell.reusableIdentifier(),
              nibName: Cell.self.xibName?() ?? Cell.reusableIdentifier(),
              bundle: Cell.self.bundle?() ?? Bundle.main,
              configurator: configurator)
  }
}



