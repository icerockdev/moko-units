/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit

public protocol UITableViewEditableCellUnitProtocol {
  var editActions: [UITableViewRowAction] { get set }
}

public class UITableViewEditableCellUnit<Cell: Fillable>: UITableViewCellUnit<Cell>, UITableViewEditableCellUnitProtocol {
  public var editActions: [UITableViewRowAction] = []
  
  public init(data: Cell.DataType,
              reuseId: String,
              nibName: String,
              configurator: ConfiguratorType?,
              height: NSNumber? = nil,
              editActions: [UITableViewRowAction]) {
    super.init(data: data, reuseId: reuseId, nibName: nibName, configurator: configurator, height: height)
    if editActions.count > 0 {
      self.editActions = editActions
    }
  }
  
}

public extension UITableViewEditableCellUnit where Cell: Reusable {
  public convenience init(data: Cell.DataType,
                          configurator: ConfiguratorType?,
                          height: NSNumber? = nil,
                          editActions: [UITableViewRowAction]) {
    self.init(data: data, reuseId: Cell.reusableIdentifier(), nibName: Cell.self.xibName?() ?? Cell.reusableIdentifier(), configurator: configurator, height: height, editActions: editActions)
  }
}

