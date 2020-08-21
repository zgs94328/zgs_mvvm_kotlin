package com.zgs.zgsmvvmkt.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.zgs.zgsmvvmkt.model.bean.AriticleBean

/**
 * Create DiffCallback
 */
class DiffDemoCallback :
    DiffUtil.ItemCallback<AriticleBean>() {
    /**
     * Determine if it is the same item
     *
     *
     * 判断是否是同一个item
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    override fun areItemsTheSame(
        oldItem: AriticleBean,
        newItem: AriticleBean
    ): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * When it is the same item, judge whether the content has changed.
     *
     *
     * 当是同一个item时，再判断内容是否发生改变
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    override fun areContentsTheSame(
        oldItem: AriticleBean,
        newItem: AriticleBean
    ): Boolean {
        return oldItem.title == newItem.title && oldItem.desc == newItem.desc && oldItem.publishTime == newItem.publishTime
    }

    /**
     * Optional implementation
     * Implement this method if you need to precisely modify the content of a view.
     * If this method is not implemented, or if null is returned, the entire item will be refreshed.
     *
     * 可选实现
     * 如果需要精确修改某一个view中的内容，请实现此方法。
     * 如果不实现此方法，或者返回null，将会直接刷新整个item。
     *
     * @param oldItem Old data
     * @param newItem New data
     * @return Payload info. if return null, the entire item will be refreshed.
     */
    override fun getChangePayload(
        oldItem: AriticleBean,
        newItem: AriticleBean
    ): Any? {
        return null
    }
}