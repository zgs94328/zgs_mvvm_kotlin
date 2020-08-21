package com.zgs.zgsmvvmkt.core.base

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zgs.baselibrary.base.BaseFragment
import com.zgs.baselibrary.util.Tip
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.core.ext.commonInit
import com.zgs.zgsmvvmkt.core.ext.showLoading
import com.zgs.zgsmvvmkt.ui.fragment.main.MainFragment
import com.zgs.zgsmvvmkt.util.loadCallBack.LoadingCallback
import kotlinx.android.synthetic.main.layout_swipe_refresh_recycler.*

/**
 *  @author 张国胜
 *  @time 2020/7/6
 *  @desc: 下拉刷新页面基类
 */
abstract class BaseSwipeListFragment<T : RecyclerView.Adapter<*>> : BaseFragment() {
    //界面状态管理者
    lateinit var loadsir: LoadService<Any>
    protected lateinit var adapter: T
    abstract fun initAdapter(): T
    override fun layoutId(): Int = R.layout.layout_swipe_refresh_recycler
    var exitTime = 0L
    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = LoadSir.getDefault().register(smartRefreshLayout, Callback.OnReloadListener {
            onRetry(it)
        })
        adapter = initAdapter()

        recyclerView.commonInit(adapter)

        smartRefreshLayout.setOnRefreshListener {
            onRefresh(it)
        }
        smartRefreshLayout.setOnLoadMoreListener {
            onLoadMore(it)
        }
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val nav = activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }
                    if (nav?.currentDestination != null && nav.currentDestination!!.id != R.id.mainFragment) {
                        //如果当前界面不是主页，那么直接调用返回即可
                        nav?.navigateUp()
                    } else {
                        //是主页
                        if (System.currentTimeMillis() - exitTime > 2000) {
                            Tip.show("再按一次退出程序")
                            exitTime = System.currentTimeMillis()
                        } else {
                            activity?.finish()
                        }
                    }
                }
            })
    }

    abstract fun onRefresh(refreshlayout: RefreshLayout)
    abstract fun onLoadMore(refreshlayout: RefreshLayout)
    abstract fun onRetry(view: View)



}