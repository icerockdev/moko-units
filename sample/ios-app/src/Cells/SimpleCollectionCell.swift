//
//  SimpleCollectionCell.swift
//  TestProj
//
//  Created by Andrew Kovalev on 09.01.2020.
//  Copyright © 2020 IceRock Development. All rights reserved.
//

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
