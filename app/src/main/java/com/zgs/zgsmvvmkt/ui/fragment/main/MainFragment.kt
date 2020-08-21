package com.zgs.zgsmvvmkt.ui.fragment.main

import android.os.Bundle
import androidx.navigation.Navigation
import com.zgs.baselibrary.base.BaseFragment
import com.zgs.baselibrary.ext.view.setOnClickNoRepeatListener
import com.zgs.baselibrary.navigation.NavHostFragment
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.core.ext.init
import com.zgs.zgsmvvmkt.core.ext.initMain
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction

/**
 *  @author 张国胜
 *  @time 2020/7/4
 *  @desc:
 */
class MainFragment:BaseFragment() {
    override fun layoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        mainViewpager.initMain(this)
        mainBottom.init {
            when (it) {
                R.id.menu_main -> mainViewpager.setCurrentItem(0, false)
                R.id.menu_project -> mainViewpager.setCurrentItem(1, false)
                R.id.menu_system -> mainViewpager.setCurrentItem(2, false)
                R.id.menu_public -> mainViewpager.setCurrentItem(3, false)
                R.id.menu_me -> mainViewpager.setCurrentItem(4, false)
            }
        }
    }

    override fun lazyLoadData() {

    }

    override fun createObserver() {
    }


}