package com.zgs.zgsmvvmkt.ui.login

import com.zgs.baselibrary.base.BaseActivity
import com.zgs.zgsmvvmkt.util.SettingUtil
import com.zgs.baselibrary.util.StatusBarUtil
import com.zgs.zgsmvvmkt.R

/**
 *  @author 张国胜
 *  @time 2020/5/28
 *  @desc:
 */
class LoginActivity : BaseActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_login_and_regist

    override fun initView() {
        StatusBarUtil.setTranslucent(this)
    }

    override fun initData() {
    }

    override fun startObserve() {
    }
}