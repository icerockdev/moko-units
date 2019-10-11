/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import Rswift

public extension UIAnyCellUnit {
  public convenience init<Reusable: Rswift.ReuseIdentifierType & Rswift.NibResourceType>(
    data: Cell.DataType,
    reusable: Reusable,
    configurator: ConfiguratorType?) where Reusable.ReusableType == Cell {
    self.init(data: data, reuseId: reusable.identifier, nibName: reusable.name, configurator: configurator)
  }
}

public extension UITableViewCellUnit {
  public convenience init<Reusable: Rswift.ReuseIdentifierType & Rswift.NibResourceType>(
    data: Cell.DataType,
    reusable: Reusable,
    configurator: ConfiguratorType?,
    height: NSNumber? = nil) where Reusable.ReusableType == Cell {
    self.init(data: data, reuseId: reusable.identifier, nibName: reusable.name, configurator: configurator, height: height)
  }
}



