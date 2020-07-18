package com.zgs.zgsmvvmkt.ui.fragment.main.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kingja.loadsir.core.LoadService
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zgs.baselibrary.base.BaseFragment
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.adapter.ArticleAdapter
import com.zgs.zgsmvvmkt.core.base.BaseSwipeListFragment
import com.zgs.zgsmvvmkt.core.ext.loadListData
import com.zgs.zgsmvvmkt.core.ext.showLoading
import com.zgs.zgsmvvmkt.model.bean.AriticleBean
import com.zgs.zgsmvvmkt.viewmodel.HomeModel
import kotlinx.android.synthetic.main.layout_swipe_refresh_recycler.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *  @author 张国胜
 *  @time 2020/7/5
 *  @desc: 热门
 */
class HotArticleFragment : BaseSwipeListFragment<ArticleAdapter>() {


    private val homeModel: HomeModel by viewModel()
    override fun lazyLoadData() {
        loadsir.showLoading()
        homeModel.getHomeList(true)
        adapter.loadMoreModule
    }

    override fun createObserver() {
        homeModel.listState.observe(viewLifecycleOwner, Observer {
            loadListData(it, adapter, loadsir, smartRefreshLayout)
        })
    }

    override fun initAdapter(): ArticleAdapter = ArticleAdapter(R.layout.adapter_article)

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
}