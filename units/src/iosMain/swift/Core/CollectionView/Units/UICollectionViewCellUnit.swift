import Foundation
import MultiPlatformLibrary
import MultiPlatformLibraryUnits
import Rswift

open class UICollectionViewCellUnit<Cell: Fillable>: CollectionUnitItem, UIAnyCellUnitProtocol {
	public var bundle: Bundle

	public func bind(cell: UICollectionViewCell) {
		fill(cell: cell)
	}

	public var itemId: Int64 {
    get {
      // FIXME pass real itemId and calculate diff by it
      return -1
    }
  }

	public var reusableIdentifier: String {
    get {
      return self.reuseId
    }
  }

	public func register(intoView: Any?) {
		guard let collectionView = intoView as? UICollectionView else {
      return
    }
    guard let xibName: String = (Cell.self as? Reusable.Type)?.xibName?() ?? NSStringFromClass(type(of: Cell.self) as! AnyClass).components(separatedBy: ".").last else {
      return
    }
    collectionView.register(UINib(nibName: xibName, bundle: Bundle.main), forCellWithReuseIdentifier: self.reuseId)
	}

  public typealias ConfiguratorType = ((_ cell: Cell) -> Void)

  public var data: Cell.DataType //Собственно данные которые попадут в FillableProtocol.fill и .update
  public var reuseId: String //обязательно указываем обычный reusable identifier
  public var nibName: String
  public var configurator: ConfiguratorType?
  public var onSelected: (() -> Void)?

	public init(data: Cell.DataType, reuseId: String, nibName: String, configurator: ConfiguratorType?, bundle: Bundle = Bundle.main) {
    self.data = data
    self.reuseId = reuseId
    self.configurator = configurator
    self.nibName = nibName
		self.bundle = bundle
  }

  public func fill(cell: Any) {
    guard let cell = cell as? Cell else { return }

    configurator?(cell)

    cell.fill(data)
  }

  public func update(cell: Any) {
    guard let cell = cell as? Cell else { return }

    cell.update(data)
  }

  public func itemData() -> Any {
    return data
  }
}
