package com.zgs.zgsmvvmkt.model.bean

import com.zgs.zgsmvvmkt.core.ext.toHtml
import kotlinx.android.parcel.Parcelize

/**
 * 文章
 */

data class AriticleBean(
    var apkLink: String,
    var author: String,//作者
    var chapterId: Int,
    var chapterName: String,
    var collect: Boolean,//是否收藏
    var courseId: Int,
    var desc: String,
    var envelopePic: String,
    var fresh: Boolean,
    var id: Int,
    var link: String,
    var niceDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var superChapterId: Int,
    var superChapterName: String,
    var shareUser: String,
    var tags: List<TagsBean>,
    var title: String,
    var type: Int,
    var userId: Int,
    var visible: Int,
    var zan: Int
) {
    var name: String = ""
        get() = when {
            author.isNotEmpty() -> author
            shareUser.isNotEmpty() -> shareUser
            else -> "匿名"
        }

    var descHtml: String = ""
        get() = desc.toHtml().toString()

}
