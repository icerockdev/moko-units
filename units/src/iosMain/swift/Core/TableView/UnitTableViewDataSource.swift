/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit

open class UnitTableViewDataSource: NSObject {
  private var registeredIds: Set<String> = Set()
  
  open func setup(for tableView: UITableView) {
    applyRowsAndSource(for: tableView)
    tableView.delegate = self
  }
  
  open var deselectCells: Bool = true
  
  @objc open func unit(from indexPath: IndexPath) -> UITableViewCellUnitProtocol? {
    return nil
  }
  
  open func updateVisibleCells(for tableView: UITableView) {
    for cell in tableView.visibleCells {
      guard let indexPath = tableView.indexPath(for: cell),
        let item = unit(from: indexPath) else {
          continue
      }
      
      item.fill(cell: cell)
    }
  }
}

extension UnitTableViewDataSource: UITableViewDataSource {
  open func tableView(_ tableView: UITableView,
                      numberOfRowsInSection section: Int) -> Int {
    return 0
  }
  
  open func tableView(_ tableView: UITableView,
                      cellForRowAt indexPath: IndexPath) -> UITableViewCell {
    guard let unit = self.unit(from: indexPath) else { return UITableViewCell() }
    
    let reuseId = unit.reuseId
    if !registeredIds.contains(reuseId) {
      let nib = UINib(nibName: unit.nibName, bundle: unit.bundle)
      tableView.register(nib, forCellReuseIdentifier: reuseId)
      registeredIds.insert(reuseId)
    }
    
    let cell = tableView.dequeueReusableCell(withIdentifier: reuseId, for: indexPath)
    unit.fill(cell: cell)
    return cell
  }
}

extension UnitTableViewDataSource: UITableViewDelegate {
  open func tableView(_ tableView: UITableView,
                      willDisplay cell: UITableViewCell,
                      forRowAt indexPath: IndexPath) {
    guard let item = unit(from: indexPath) else { return }
    item.update(cell: cell)
  }
  
  open func tableView(_ tableView: UITableView,
                      heightForRowAt indexPath: IndexPath) -> CGFloat {
    if let height = unit(from: indexPath)?.height?.floatValue {
      return CGFloat(height)
    } else {
      return tableView.rowHeight
    }
  }
  
  open func tableView(_ tableView: UITableView,
                      estimatedHeightForRowAt indexPath: IndexPath) -> CGFloat {
    if let height = unit(from: indexPath)?.estimatedHeight?.floatValue {
      return CGFloat(height)
    } else {
      return tableView.estimatedRowHeight
    }
  }
  
  open func tableView(_ tableView: UITableView,
                      canEditRowAt indexPath: IndexPath) -> Bool {
    return (unit(from: indexPath) as? UITableViewEditableCellUnitProtocol)?.editActions.isEmpty == false
  }
  
  open func tableView(_ tableView: UITableView,
                      editActionsForRowAt indexPath: IndexPath) -> [UITableViewRowAction]? {
    return (unit(from: indexPath) as? UITableViewEditableCellUnitProtocol)?.editActions
  }
  
  open func tableView(_ tableView: UITableView,
                      didSelectRowAt indexPath: IndexPath) {
    if deselectCells {
      tableView.deselectRow(at: indexPath, animated: true)
    }
    unit(from: indexPath)?.onSelected?()
  }
}

public class UnitTableViewDelegate: NSObject, UITableViewDelegate {
  let tableSource: UnitTableViewDataSource
  let tableViewDelegate: UITableViewDelegate
  
  public init(decorate delegate: UITableViewDelegate, tableSource: UnitTableViewDataSource) {
    self.tableSource = tableSource
    self.tableViewDelegate = delegate
  }
  
  public override func forwardingTarget(for aSelector: Selector!) -> Any? {
    if tableSource.responds(to: aSelector) {
      return tableSource
    } else {
      return tableViewDelegate
    }
  }
  
  public override func responds(to aSelector: Selector!) -> Bool {
    return tableSource.responds(to: aSelector) || tableViewDelegate.responds(to: aSelector)
  }
}

public extension UnitTableViewDataSource {
  fileprivate func applyRowsAndSource(for tableView: UITableView) {
    tableView.rowHeight = UITableView.automaticDimension
    tableView.estimatedRowHeight = UITableView.automaticDimension
    tableView.dataSource = self
  }
  
  public func setup(for tableView: UITableView, delegate: UITableViewDelegate) -> UITableViewDelegate {
    applyRowsAndSource(for: tableView)
    let decoratedDelegate = UnitTableViewDelegate(decorate: delegate, tableSource: self)
    tableView.delegate = decoratedDelegate
    return decoratedDelegate
  }
}

