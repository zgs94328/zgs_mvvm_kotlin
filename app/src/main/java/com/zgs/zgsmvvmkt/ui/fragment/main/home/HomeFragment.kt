package com.zgs.zgsmvvmkt.ui.fragment.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zgs.baselibrary.base.BaseFragment
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.core.ext.commonInit
import com.zgs.zgsmvvmkt.core.ext.init
import com.zgs.zgsmvvmkt.core.ext.initHome
import com.zgs.baselibrary.util.Tip
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  @author 张国胜
 *  @time 2020/7/1
 *  @desc: 主页
 */
class HomeFragment : BaseFragment() {
    private var titleList =
        arrayListOf<String>("热门", "最新", "每日一问", "面试", "自定义控件", "kotlin", "java深入", "干活资源", "基础知识")
    var fragments: ArrayList<Fragment> = arrayListOf()
    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
        titleList.forEach { _ ->
            fragments.add(HotArticleFragment())
        }
        viewflipper.init(titleList) {
            Tip.show(titleList[it])
        }


        viewpager.commonInit(this, fragments)
        magic_indicator.initHome(viewpager, titleList)
    }

    override fun lazyLoadData() {
    }

    override fun createObserver() {

    }
}