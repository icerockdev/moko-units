/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit

@objc
public protocol Reusable: class {
  static func reusableIdentifier() -> String
  
  @objc optional static func xibName() -> String
  @objc optional static func bundle() -> Bundle
}

