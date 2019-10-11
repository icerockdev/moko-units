/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit

open class UIAnyCellUnit<Cell: Fillable>: NSObject, UIAnyCellUnitProtocol {
  public typealias ConfiguratorType = ((_ cell: Cell) -> Void)
  
  public var data: Cell.DataType
  public var reuseId: String
  public var nibName: String
  public var bundle: Bundle
  public var configurator: ConfiguratorType?
  public var onSelected: (() -> Void)?
  
  init(data: Cell.DataType,
       reuseId: String,
       nibName: String,
       bundle: Bundle = Bundle.main,
       configurator: ConfiguratorType?) {
    self.data = data
    self.reuseId = reuseId
    self.nibName = nibName
    self.bundle = bundle
    self.configurator = configurator
  }
  
  public func fill(cell: Any) {
    guard let cell = cell as? Cell else { return }
    
    configurator?(cell)
    
    cell.fill(data)
  }
  
  public func update(cell: Any) {
    guard let cell = cell as? Cell else { return }
    
    cell.update(data)
  }
  
  public func itemData() -> Any {
    return data
  }
}

