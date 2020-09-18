package com.zgs.zgsmvvmkt.ui.fragment.setting

import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.adapter.ArticleAdapter
import com.zgs.zgsmvvmkt.adapter.diffutil.DiffDemoCallback
import com.zgs.zgsmvvmkt.core.base.BaseSwipeListFragment
import com.zgs.zgsmvvmkt.core.ext.showLoading
import com.zgs.zgsmvvmkt.viewmodel.HomeModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import org.koin.android.viewmodel.ext.android.viewModel


/**
 *  @author 张国胜
 *  @time 2020/8/12
 *  @desc:
 */
class SettingFragment : BaseSwipeListFragment<ArticleAdapter>() {


    private val homeModel: HomeModel by viewModel()
    override fun lazyLoadData() {
        // Your can change the status out of Main thread.
        Thread(Runnable {
            loadsir.showLoading()
        }).start()
        // 必须设置Diff Callback
//        loadsir.showLoading()
        adapter.setDiffCallback(DiffDemoCallback())
        Handler().postDelayed({ // do net here...

            //callback
            homeModel.getHomeList(true)

        }, 200)
    }

    override fun createObserver() {
        homeModel.listState.observe(viewLifecycleOwner, Observer {
            adapter.addData(it.listData)
//            adapter.notifyDataSetChanged()
            loadsir.showSuccess()

        })
        homeModel.testValue.observe(viewLifecycleOwner, Observer {
            println(it)
        })
    }



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
    override fun initAdapter(): ArticleAdapter{
        var adapter =  ArticleAdapter(R.layout.adapter_article_item)
        adapter.setDiffCallback(DiffDemoCallback())
        adapter.setOnItemClickListener { adapter, view, position ->
            nav().navigateAction(R.id.action_hotArticleFragment_to_settingFragment)
        }
        adapter.setOnItemChildClickListener { adapter, view, position ->
            homeModel.testValue.value="哈哈"
        }
        return adapter
    }
}