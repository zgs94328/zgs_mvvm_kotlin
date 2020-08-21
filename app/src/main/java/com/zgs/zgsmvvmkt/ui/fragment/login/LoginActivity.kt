package com.zgs.zgsmvvmkt.ui.fragment.login

import android.widget.CompoundButton
import com.zgs.baselibrary.base.BaseActivity
import com.zgs.zgsmvvmkt.BR
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.viewmodel.RegistAndLoginModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *  @author 张国胜
 *  @time 2020/5/28
 *  @desc:
 */
class LoginActivity : BaseActivity() {
    private val viewModel: RegistAndLoginModel by viewModel()
    override fun getLayoutResId(): Int = R.layout.activity_login_and_regist

    override fun initView() {
    }

    override fun initData() {
        mBinding.setVariable(BR.viewmodel, viewModel)
        mBinding.setVariable(BR.click, ProxyClick())


    }

    override fun startObserve() {
    }

    inner class ProxyClick {
        var onCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isShowPwd.set(isChecked)
            }

        fun goBack() {
            finish()
        }

        fun registOrLogin() {

        }
    }
}