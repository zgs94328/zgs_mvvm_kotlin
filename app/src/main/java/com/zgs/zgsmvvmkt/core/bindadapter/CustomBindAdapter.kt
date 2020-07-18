package com.zgs.zgsmvvmkt.core.bindadapter

import android.text.InputType
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.databinding.BindingAdapter

/**
 *  @author 张国胜
 *  @time 2020/6/28
 *  @desc:
 */
object CustomBindAdapter {
    @BindingAdapter(value = ["checkChange"])
    @JvmStatic
    fun checkChange(checkbox: CheckBox, listener: CompoundButton.OnCheckedChangeListener) {
        checkbox.setOnCheckedChangeListener(listener)
    }
    @BindingAdapter(value = ["showPwd"])
    @JvmStatic
    fun showPwd(et: EditText, boolean: Boolean) {
        if (boolean) {
            et.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            et.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        et.setSelection(et.text.length)
    }

}