package com.zgs.zgsmvvmkt.ui.fragment.setting

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.diff.BrvahAsyncDiffer
import com.chad.library.adapter.base.diff.BrvahAsyncDifferConfig
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.adapter.ArticleAdapter
import com.zgs.zgsmvvmkt.adapter.diffutil.DiffDemoCallback
import com.zgs.zgsmvvmkt.core.base.BaseSwipeListFragment
import com.zgs.zgsmvvmkt.core.ext.loadListData
import com.zgs.zgsmvvmkt.core.ext.showLoading
import com.zgs.zgsmvvmkt.viewmodel.HomeModel
import kotlinx.android.synthetic.main.layout_swipe_refresh_recycler.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 *  @author 张国胜
 *  @time 2020/8/12
 *  @desc:
 */
class SettingFragment : BaseSwipeListFragment<ArticleAdapter>() {


    private val homeModel: HomeModel by viewModel()
    override fun lazyLoadData() {

//        // Your can change the status out of Main thread.
            loadsir.showLoading()

        // 必须设置Diff Callback
//        adapter.setDiffCallback(DiffDemoCallback())

        homeModel.getHomeList(true)
    }

    override fun createObserver() {
        homeModel.listState.observe(viewLifecycleOwner, Observer {
            adapter.setList(it.listData);
            loadsir.showSuccess()

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
}