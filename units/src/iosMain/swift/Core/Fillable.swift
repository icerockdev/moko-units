/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation

public protocol Fillable {
  associatedtype DataType
  
  func fill(_ data: DataType)
}
