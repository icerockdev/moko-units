/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit

public extension UICollectionView {
  public func registerReusableCell(_ reusable: Reusable.Type) {
    if let xibName = reusable.xibName?() {
      let nib = UINib(nibName: xibName, bundle: reusable.bundle?())
      register(nib, forCellWithReuseIdentifier: reusable.reusableIdentifier())
    } else {
      register(reusable.self, forCellWithReuseIdentifier: reusable.reusableIdentifier())
    }
  }
}

