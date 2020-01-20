/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import Rswift

public extension UITableViewCellUnit {
  public convenience init<ResourceType: Rswift.ReuseIdentifierType & Rswift.NibResourceType>(
    data: Cell.DataType,
    itemId: Int64,
    reusable: ResourceType,
    configurator: ConfiguratorType?) where ResourceType.ReusableType == Cell {
    self.init(data: data, itemId: itemId, reuseId: reusable.identifier, nibName: reusable.name, bundle: reusable.bundle, configurator: configurator)
  }
}



