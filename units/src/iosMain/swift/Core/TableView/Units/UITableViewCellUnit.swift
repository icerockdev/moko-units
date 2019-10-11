/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit

@objc public protocol UITableViewCellUnitProtocol: UIAnyCellUnitProtocol {
  var height: NSNumber? { get }
  var estimatedHeight: NSNumber? { get }
}

open class UITableViewCellUnit<Cell: Fillable>: UIAnyCellUnit<Cell>, UITableViewCellUnitProtocol {
  public typealias ConfiguratorType = ((_ cell: Cell) -> Void)
  
  open var height: NSNumber? //Если требуется - высота ячейки
  open var estimatedHeight: NSNumber?
  
  public init(
    data: Cell.DataType,
    reuseId: String,
    nibName: String,
    bundle: Bundle = Bundle.main,
    configurator: ConfiguratorType?,
    height: NSNumber? = nil,
    estimatedHeight: NSNumber? = nil
    ) {
    super.init(
      data: data,
      reuseId: reuseId,
      nibName: nibName,
      bundle: bundle,
      configurator: configurator
    )
    self.height = height
    self.estimatedHeight = estimatedHeight
  }
}

public extension UITableViewCellUnit where Cell: Reusable {
  public convenience init(
    data: Cell.DataType,
    configurator: ConfiguratorType?,
    height: NSNumber? = nil,
    estimatedHeight: NSNumber? = nil
    ) {
    self.init(
      data: data,
      reuseId: Cell.reusableIdentifier(),
      nibName: Cell.self.xibName?() ?? Cell.reusableIdentifier(),
      bundle: Cell.self.bundle?() ?? Bundle.main,
      configurator: configurator,
      height: height,
      estimatedHeight: estimatedHeight)
  }
}

