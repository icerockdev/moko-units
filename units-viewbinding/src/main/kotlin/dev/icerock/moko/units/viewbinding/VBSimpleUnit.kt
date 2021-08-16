/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.units.viewbinding

class VBSimpleUnit(
    private val ld: LiveData<String>,
) : VBTableUnitItem<LayoutSimpleUnitBinding>() {
    private lateinit var lifecycleOwner: LifecycleOwner
    override fun inflate(
        inflater: LayoutInflater,
        parent: ViewGroup,
        lifecycleOwner: LifecycleOwner
    ): LayoutSimpleUnitBinding {
        this.lifecycleOwner = lifecycleOwner
        return LayoutSimpleUnitBinding.inflate(inflater, parent, false)
    }

    override fun bind(context: Context, binding: LayoutSimpleUnitBinding) {
        val observer = object : Observer<String> {
            override fun onChanged(t: String?) {
                println("observed $t")
            }
        }
        ld.observe(lifecycleOwner, observer)
    }
