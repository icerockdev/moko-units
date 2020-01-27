/*
* Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
*/


import UIKit
import MultiPlatformLibrary

public extension TableUnitsSourceKt {
  public static func `default`(for tableView: UITableView) -> TableUnitsSource {
    return TableUnitsSourceKt.create(forTableView: tableView) { (view, _, _) in
      view.reloadData()
    }
  }
}

public extension CollectionUnitsSourceKt {
  public static func `default`(for collectionView: UICollectionView) -> CollectionUnitsSource {
    return CollectionUnitsSourceKt.create(forCollectionView: collectionView) { (view, _, _) in
      view.reloadData()
    }
  }
}
