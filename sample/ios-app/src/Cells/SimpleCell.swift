/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit
import MultiPlatformLibraryUnits

class SimpleCell: UITableViewCell, Fillable {
    typealias DataType = CellModel
    
    struct CellModel {
        let id: Int64
        let title: String
    }
    
    @IBOutlet private var titleLabel: UILabel!
    
    func fill(_ data: SimpleCell.CellModel) {
        titleLabel.text = data.title
    }
}
