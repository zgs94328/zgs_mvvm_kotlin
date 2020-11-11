package com.zgs.zgsmvvmkt.ui.fragment.main.mine

import android.os.Bundle
import com.zgs.baselibrary.base.BaseFragment
import com.zgs.zgsmvvmkt.R
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction

/**
 *  @author 张国胜
 *  @time 2020/7/1
 *  @desc: 我的
 */
class MineFragment : BaseFragment() {
    override fun layoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun lazyLoadData() {
    }

    override fun createObserver() {
    }

    inner class ProxyClick {
        /** 设置 */
        fun setting() {
            nav().navigateAction(R.id.action_hotArticleFragment_to_settingFragment)
        }
    }
}