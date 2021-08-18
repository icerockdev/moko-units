package com.icerockdev

import androidx.databinding.ViewDataBinding
import dev.icerock.moko.units.databinding.DataBindingDropDownUnitItem
import kotlin.Int

class UnitSimpleDropdown : UnitSimple(), DataBindingDropDownUnitItem {
  override val dropDownLayoutId: Int = R.layout.unit_simple_dropdown

  override fun bindDropDown(viewDataBinding: ViewDataBinding) {
    viewDataBinding.setVariable(BR.number, number)
    viewDataBinding.setVariable(BR.obj1, obj1)
    viewDataBinding.setVariable(BR.obj2, obj2)
    viewDataBinding.setVariable(BR.text, text)
  }
}
