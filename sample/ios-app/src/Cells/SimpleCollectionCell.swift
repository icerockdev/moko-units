/*
* Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
*/

import Foundation
import MultiPlatformLibraryUnits

class SimpleCollectionCell: UICollectionViewCell, Fillable {

    typealias DataType = CellModel

    struct CellModel {
        let id: Int64
        let title: String
    }

    @IBOutlet private var titleLabel: UILabel!

    func fill(_ data: SimpleCollectionCell.CellModel) {
        titleLabel.text = data.title
    }

    func update(_ data: SimpleCollectionCell.CellModel) {

    }}
