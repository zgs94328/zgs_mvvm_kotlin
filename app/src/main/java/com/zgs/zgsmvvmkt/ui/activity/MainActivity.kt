package com.zgs.zgsmvvmkt.ui.activity

import com.zgs.baselibrary.base.BaseActivity
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.core.ext.init
import com.zgs.zgsmvvmkt.core.ext.initMain
import com.zgs.zgsmvvmkt.viewmodel.HomeModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val homeModel: HomeModel by viewModel()
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {
    }

    override fun startObserve() {
    }


}
