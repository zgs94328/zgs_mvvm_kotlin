package com.zgs.zgsmvvmkt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.model.bean.AriticleBean


/**
 *  @author 张国胜
 *  @time 2020/7/12
 *  @desc:
 */
class ArticleAdapter2: RecyclerView.Adapter<ArticleAdapter2.ViewHoleder>() {
    var list = arrayListOf<AriticleBean>()
    inner class ViewHoleder(view:View):RecyclerView.ViewHolder(view){

    }
    fun addData(datas:ArrayList<AriticleBean>){
        list.addAll(datas)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoleder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_article, parent, false)
        return ViewHoleder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHoleder, position: Int) {
    }
}