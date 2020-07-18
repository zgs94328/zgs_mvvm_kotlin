package com.zgs.zgsmvvmkt.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import me.hgj.jetpackmvvm.callback.databind.BooleanObservableField
import me.hgj.jetpackmvvm.callback.databind.StringObservableField
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toErrShowResponse

/**
 *  @author 张国胜
 *  @time 2020/6/24
 *  @desc:
 */
class RegistAndLoginModel : ViewModel() {
    var isShowPwd = BooleanObservableField()
    var userName = StringObservableField()
    var userPassWord = StringObservableField()
    fun loginOrRegist() = rxLifeScope.launch {
    }


}