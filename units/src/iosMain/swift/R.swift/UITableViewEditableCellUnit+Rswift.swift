/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import Rswift

public extension UITableViewEditableCellUnit {
  public convenience init<Reusable: Rswift.ReuseIdentifierType & Rswift.NibResourceType>(
    data: Cell.DataType,
    reusable: Reusable,
    configurator: ConfiguratorType?,
    height: NSNumber? = nil,
    editActions: [UITableViewRowAction]) where Reusable.ReusableType == Cell {
    self.init(data: data, reuseId: reusable.identifier, nibName: reusable.name, configurator: configurator, height: height, editActions: editActions)
  }
}

