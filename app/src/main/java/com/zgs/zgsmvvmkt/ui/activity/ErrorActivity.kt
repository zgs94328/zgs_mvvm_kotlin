package com.zgs.zgsmvvmkt.ui.activity

import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import com.zgs.baselibrary.base.BaseActivity
import com.zgs.baselibrary.ext.view.setOnClickNoRepeatListener
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.core.ext.init
import kotlinx.android.synthetic.main.activity_error.*
import kotlinx.android.synthetic.main.layout_title_bar.*

/**
 *  @author 张国胜
 *  @time 2020/8/12
 *  @desc:
 */
class ErrorActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_error
    }

    override fun initView() {
        toolbar.init("发生错误")
    }

    override fun initData() {
        val config = CustomActivityOnCrash.getConfigFromIntent(intent)
        setOnClickNoRepeatListener(errorRestart){
            config?.run {
                CustomActivityOnCrash.restartApplication(this@ErrorActivity, this)
            }
        }
    }

    override fun startObserve() {
    }
}