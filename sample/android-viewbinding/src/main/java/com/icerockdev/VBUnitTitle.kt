package com.icerockdev

//import android.content.Context
//import android.view.View
//import androidx.lifecycle.LifecycleOwner
//import com.icerockdev.databinding.UnitTitleBinding
//import dev.icerock.moko.units.viewbinding.VBTableUnitItem
//
//class VBUnitTitle(
//    override var itemId: Long,
//    private val text1: String,
//    private val butTitle: String,
//    private val text2: String,
//    private val text3: String
//) : VBTableUnitItem<UnitTitleBinding>() {
//    override val layoutId: Int
//        get() = R.layout.unit_title
//    
//    override fun bindView(view: View): UnitTitleBinding {
//        return UnitTitleBinding.bind(view)
//    }
//
//    override fun UnitTitleBinding.bindData(
//        context: Context,
//        lifecycleOwner: LifecycleOwner,
//        viewHolder: VBViewHolder<UnitTitleBinding>,
//    ) {
//        this.firstText.text = text1
//        this.secondText.text = text2
//        this.unitButton.text = butTitle
//        this.unitTitle.text = text3
//    }
//}