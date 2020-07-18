package com.zgs.zgsmvvmkt.core.base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zgs.baselibrary.base.BaseFragment
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.core.ext.commonInit
import kotlinx.android.synthetic.main.layout_swipe_refresh_recycler.*

/**
 *  @author 张国胜
 *  @time 2020/7/6
 *  @desc: 下拉刷新页面基类
 */
abstract class BaseSwipeListFragment<T : RecyclerView.Adapter<*>> : BaseFragment() {
    //界面状态管理者
    protected lateinit var loadsir: LoadService<Any>
    protected lateinit var adapter: T
    abstract fun initAdapter(): T
    override fun layoutId(): Int = R.layout.layout_swipe_refresh_recycler

    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = LoadSir.getDefault().register(smartRefreshLayout, Callback.OnReloadListener {
            onRetry(it)
        });
        adapter = initAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
//        recyclerView.commonInit(adapter)
        // 第一种，直接取消动画

        // 第一种，直接取消动画
        val animator: RecyclerView.ItemAnimator? =
            recyclerView.itemAnimator
        if (animator is SimpleItemAnimator) {
            (animator as SimpleItemAnimator).supportsChangeAnimations = false
        }
        smartRefreshLayout.setOnRefreshListener {
            onRefresh(it)
        }
        smartRefreshLayout.setOnLoadMoreListener {
            onLoadMore(it)
        }

    }

    abstract fun onRefresh(refreshlayout: RefreshLayout)
    abstract fun onLoadMore(refreshlayout: RefreshLayout)
    abstract fun onRetry(view: View)

}