/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import Foundation
import UIKit

public struct TableViewSectionUnitConfig {
  public struct TitleConfig {
    public var text: String?
    public var textColor: UIColor?
    public var font: UIFont?
    public var height: CGFloat?
    public var backgroundColor: UIColor?
    public var textAlignment: NSTextAlignment?
    
    public init(text: String? = nil,
                textColor: UIColor? = nil,
                font: UIFont? = nil,
                height: CGFloat? = nil,
                backgroundColor: UIColor? = nil,
                textAlignment: NSTextAlignment? = .left) {
      self.text = text
      self.textColor = textColor
      self.font = font
      self.height = height
      self.backgroundColor = backgroundColor
      self.textAlignment = textAlignment
    }
  }
  
  public var header: TitleConfig?
  public var footer: TitleConfig?
  
  public var headerView: UIView?
  public var footerView: UIView?
}

public struct TableViewSectionUnit {
  public var config: TableViewSectionUnitConfig
  public var units: [UITableViewCellUnitProtocol]
}

open class SectionedUnitTableViewDataSource: UnitTableViewDataSource {
  open var sectionUnits:[TableViewSectionUnit] = []
  
  open override func unit(from indexPath: IndexPath) -> UITableViewCellUnitProtocol? {
    return sectionUnits[indexPath.section].units[indexPath.row]
  }
  
  open func numberOfSections(in tableView: UITableView) -> Int {
    return sectionUnits.count
  }
  
  open override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    return sectionUnits[section].units.count
  }
  
  // UITableViewDelegate
  open func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
    return sectionUnits[section].config.header?.text
  }
  
  open func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
    return sectionUnits[section].config.header?.height ?? tableView.sectionHeaderHeight
  }
  
  open func tableView(_ tableView: UITableView, titleForFooterInSection section: Int) -> String? {
    return sectionUnits[section].config.footer?.text
  }
  
  open func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
    return sectionUnits[section].config.footer?.height ?? tableView.sectionFooterHeight
  }
  
  open func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
    return sectionUnits[section].config.headerView
  }
  
  open func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
    return sectionUnits[section].config.footerView
  }
  
  open func tableView(_ tableView: UITableView, willDisplayHeaderView view: UIView, forSection: Int) {
    guard let titleView = view as? UITableViewHeaderFooterView,
      let config = sectionUnits[forSection].config.header else {
        return
    }
    setupTitleView(hfView: titleView, config: config)
  }
  
  open func tableView(_ tableView: UITableView, willDisplayFooterView view: UIView, forSection section: Int) {
    guard let titleView = view as? UITableViewHeaderFooterView,
      let config = sectionUnits[section].config.footer else {
        return
    }
    setupTitleView(hfView: titleView, config: config)
  }
  
  private func setupTitleView(hfView: UITableViewHeaderFooterView, config: TableViewSectionUnitConfig.TitleConfig) {
    hfView.textLabel?.text = config.text
    
    if let color = config.textColor {
      hfView.textLabel?.textColor = color
    }
    
    if let font = config.font {
      hfView.textLabel?.font = font
    }
    
    if let textAlignment = config.textAlignment {
      hfView.textLabel?.textAlignment = textAlignment
    }
    
    if let backgroundColor = config.backgroundColor {
      hfView.contentView.backgroundColor = backgroundColor
    }
  }
}

