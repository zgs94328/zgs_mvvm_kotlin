package com.zgs.zgsmvvmkt.ui.fragment.main.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zgs.baselibrary.navigation.NavHostFragment
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.adapter.ArticleAdapter
import com.zgs.zgsmvvmkt.adapter.diffutil.DiffDemoCallback
import com.zgs.zgsmvvmkt.core.base.BaseSwipeListFragment
import com.zgs.zgsmvvmkt.core.ext.loadListData
import com.zgs.zgsmvvmkt.core.ext.showLoading
import com.zgs.zgsmvvmkt.viewmodel.HomeModel
import kotlinx.android.synthetic.main.layout_swipe_refresh_recycler.*
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *  @author 张国胜
 *  @time 2020/7/5
 *  @desc: 热门
 */
class HotArticleFragment : BaseSwipeListFragment<ArticleAdapter>() {

    private val homeModel: HomeModel by viewModel()
    override fun lazyLoadData() {

        // Your can change the status out of Main thread.
        Thread(Runnable {
            loadsir.showLoading()
        }).start()
        // 必须设置Diff Callback
        adapter.setDiffCallback(DiffDemoCallback())
        homeModel.getHomeList(true)
    }

    override fun createObserver() {
        homeModel.listState.observe(viewLifecycleOwner, Observer {
            loadListData(it, adapter, loadsir, smartRefreshLayout)
        })
    }

    override fun initAdapter(): ArticleAdapter = ArticleAdapter(R.layout.adapter_article_item)

    override fun onRefresh(refreshlayout: RefreshLayout) {
        homeModel.getHomeList(true)

    }

    override fun onLoadMore(refreshlayout: RefreshLayout) {
        homeModel.getHomeList(false)
    }

    override fun onRetry(view: View) {
        loadsir.showLoading()
        homeModel.getHomeList(true)
    }

    override fun initData() {
        super.initData()
        adapter.setOnItemClickListener { adapter, view, position ->
            nav().navigateAction(R.id.action_hotArticleFragment_to_settingFragment)
        }
    }
}