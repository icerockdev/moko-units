/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation

@objc public protocol UIAnyCellUnitProtocol {
  var reuseId: String { get }
  var nibName: String { get }
  var bundle: Bundle { get }
  var onSelected: (() -> Void)? { get set }
  func fill(cell: Any)
  func update(cell: Any)
  func itemData() -> Any
}

