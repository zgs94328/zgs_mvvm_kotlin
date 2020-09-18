package com.zgs.zgsmvvmkt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.databinding.AdapterArticleItemBinding
import com.zgs.zgsmvvmkt.model.bean.AriticleBean

/**
 *  @author 张国胜
 *  @time 2020/7/6
 *  @desc:
 */
class ArticleAdapter(layoutId: Int) :
    BaseQuickAdapter<AriticleBean, BaseDataBindingHolder<AdapterArticleItemBinding>>(layoutId) {
    override fun convert(holder: BaseDataBindingHolder<AdapterArticleItemBinding>, item: AriticleBean) {
        // 获取 Binding
        val binding: AdapterArticleItemBinding? = holder.dataBinding
        binding?.bean = item
        binding?.executePendingBindings()
        addChildClickViewIds(R.id.iv_collect)
    }
}