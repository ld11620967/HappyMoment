package com.nilin.happymoment

import android.content.Context
import android.text.format.DateUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.nilin.happymoment.bean.Contentlist
import java.text.SimpleDateFormat
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard


class VideoAdapter(var context: Context, layoutId: Int) : BaseQuickAdapter<Contentlist, BaseViewHolder>(layoutId) {

    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss")

    override fun convert(viewHolder: BaseViewHolder?, article: Contentlist?) {

        viewHolder!!.setText(R.id.time, DateUtils.getRelativeTimeSpanString(sdf.parse(article!!.create_time).time))
        viewHolder.setText(R.id.title, article.text)

        val jcVideoPlayerStandard: JCVideoPlayerStandard = viewHolder.getView<JCVideoPlayerStandard>(R.id.videoplayer)
        jcVideoPlayerStandard.setUp(article.video_uri, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, article.text.replace("\n", "").trim())
        GlideApp.with(context)
                .asBitmap()
                .load(article.video_uri)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(jcVideoPlayerStandard.thumbImageView)
    }
}


