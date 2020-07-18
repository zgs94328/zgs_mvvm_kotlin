package com.zgs.zgsmvvmkt.core.ext

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import java.util.*

/**
 *  @author 张国胜
 *  @time 2020/7/6
 *  @desc:
 */

fun ViewPager2.commonInit(
    fragment: Fragment,
    fragments: ArrayList<Fragment>,
    isUserInputEnabled: Boolean = true
): ViewPager2 {
    this.isUserInputEnabled = isUserInputEnabled
    adapter = object : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

    }
    return this
}

/**
 * 当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)
 * ，并通过Adapter的增删改插方法去刷新RecyclerView，而不是通过notifyDataSetChanged()
 * 。（其实可以直接设置为true，当需要改变宽高的时候就用notifyDataSetChanged()去整体刷新一下）
 */

fun RecyclerView.commonInit(
    bindAdapter: RecyclerView.Adapter<*>,
    orientation: Int = RecyclerView.VERTICAL,
    hasFixedSize: Boolean = true
): RecyclerView {
    LinearLayoutManager.VERTICAL
    layoutManager = LinearLayoutManager(context, orientation, false)
    setHasFixedSize(hasFixedSize)
    adapter = bindAdapter
    return this
}

