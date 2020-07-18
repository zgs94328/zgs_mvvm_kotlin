package com.zgs.zgsmvvmkt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.zgs.zgsmvvmkt.databinding.AdapterArticleBinding
import com.zgs.zgsmvvmkt.model.bean.AriticleBean

/**
 *  @author 张国胜
 *  @time 2020/7/6
 *  @desc:
 */
class ArticleAdapter(layoutId: Int) :
    BaseQuickAdapter<AriticleBean, BaseDataBindingHolder<AdapterArticleBinding>>(layoutId),LoadMoreModule {
    override fun convert(holder: BaseDataBindingHolder<AdapterArticleBinding>, item: AriticleBean) {
        // 获取 Binding

        // 获取 Binding
        val binding: AdapterArticleBinding? = holder.dataBinding
        binding?.bean = item
    }
}