package com.zgs.zgsmvvmkt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import com.zgs.zgsmvvmkt.core.stateCallback.ListDataUiState
import com.zgs.zgsmvvmkt.http.Api
import com.zgs.zgsmvvmkt.http.manager.errorMsg
import com.zgs.zgsmvvmkt.model.bean.ApiPagerBean
import com.zgs.zgsmvvmkt.model.bean.AriticleBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toErrShowResponse

/**
 *  @author 张国胜
 *  @time 2020/7/4
 *  @desc:
 */
class HomeModel : ViewModel() {

    //页码 首页数据页码从0开始
    var pageNo = 0
    var listState = MutableLiveData<ListDataUiState<AriticleBean>>()
    var testValue = MutableLiveData<String>()

    /**
     * 获取首页文章列表数据
     * @param isRefresh 是否是刷新，即第一页
     */
    fun getHomeList(isRefresh: Boolean) = rxLifeScope.launch({
        if (isRefresh) pageNo = 0
        var a = Api.getHomeListUrl(pageNo)
        var ariticleBeans = RxHttp.get(a)
            .toErrShowResponse<ApiPagerBean<ArrayList<AriticleBean>>>().await()
        val listDataUiState =
            ListDataUiState(
                isSuccess = true,
                isRefresh = isRefresh,
                isEmpty = ariticleBeans.isEmpty(),
                hasMore = ariticleBeans.hasMore(),
                isFirstEmpty = isRefresh && ariticleBeans.isEmpty(),
                listData = ariticleBeans.datas
            )

        listState.postValue(listDataUiState)
        pageNo++






}, {
        //请求失败
        val listDataUiState =
            ListDataUiState(
                isSuccess = false,
                errMessage = it.errorMsg!!,
                isRefresh = isRefresh,
                listData = arrayListOf<AriticleBean>()
            )
        listState.postValue(listDataUiState)
    })
}