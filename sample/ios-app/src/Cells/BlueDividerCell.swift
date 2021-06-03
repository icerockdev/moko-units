/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit
import MultiPlatformLibraryUnits

class BlueDividerCell: UITableViewCell, Fillable {
    typealias DataType = Void
    
    @IBOutlet private var titleLabel: UILabel!
    
    func fill(_ data: Void) {
    }
}
