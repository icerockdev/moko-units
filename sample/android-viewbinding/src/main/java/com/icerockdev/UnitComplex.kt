package com.icerockdev

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.icerockdev.library.ItemData
import dev.icerock.moko.units.databinding.DataBindingUnitItem
import kotlin.Int
import kotlin.Long
import kotlin.String

open class UnitComplex : DataBindingUnitItem {
  override var itemId: Long = RecyclerView.NO_ID

  override val layoutId: Int = R.layout.unit_complex

  var number: Int? = null

  var obj1: ItemData? = null

  var obj2: ItemData? = null

  var text: String? = null

  override fun bind(viewDataBinding: ViewDataBinding) {
    viewDataBinding.setVariable(BR.number, number)
    viewDataBinding.setVariable(BR.obj1, obj1)
    viewDataBinding.setVariable(BR.obj2, obj2)
    viewDataBinding.setVariable(BR.text, text)
  }
}
