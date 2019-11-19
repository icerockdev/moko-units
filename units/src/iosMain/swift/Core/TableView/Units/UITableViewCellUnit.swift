/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit
import MultiPlatformLibrary

@objc public protocol UITableViewCellUnitProtocol: UIAnyCellUnitProtocol {
  var height: NSNumber? { get }
  var estimatedHeight: NSNumber? { get }
}

open class UITableViewCellUnit<Cell: Fillable>: UIAnyCellUnit<Cell>, UITableViewCellUnitProtocol, TableUnitItem {
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
  
  public func bind(cell_ cell: UITableViewCell) {
    self.fill(cell: cell)
  }
  
  public var itemId: Int64 {
    get {
      // FIXME pass real itemId and calculate diff by it
      return -1
    }
  }
  
  public var reusableIdentifier: String {
    get {
      return self.reuseId
    }
  }
  
  public func register(intoView: Any?) {
    guard let tableView = intoView as? UITableView else { return }
    
    tableView.register(
      UINib(nibName: self.nibName, bundle: self.bundle),
      forCellReuseIdentifier: self.reusableIdentifier
    )
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

