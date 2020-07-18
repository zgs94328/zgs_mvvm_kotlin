package com.zgs.zgsmvvmkt.util.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.zgs.zgsmvvmkt.R


class LoadingCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

}