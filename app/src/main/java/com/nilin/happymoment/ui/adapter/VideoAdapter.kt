package com.nilin.happymoment

import android.content.Context
import android.net.Uri
import android.text.format.DateUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.nilin.happymoment.bean.Contentlist
import java.text.SimpleDateFormat
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard


class VideoAdapter(var context: Context, layoutId:Int) : BaseQuickAdapter<Contentlist, BaseViewHolder>(layoutId) {

    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss")

    override fun convert(viewHolder: BaseViewHolder?, article: Contentlist?) {

        viewHolder!!.setText(R.id.time, DateUtils.getRelativeTimeSpanString(sdf.parse(article!!.create_time).time))
        viewHolder.setText(R.id.title,article.text)

//        val myBitmap: Bitmap = Glide.load("http://somefakeurl.com/fakeImage.jpeg")
//                .asBitmap()
//                .fitCenter()
//                .into(250, 250)

        val jcVideoPlayerStandard:JCVideoPlayerStandard = viewHolder.getView<JCVideoPlayerStandard>(R.id.videoplayer)

//        jcVideoPlayerStandard.setUp(article.video_uri, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, article.text)
        jcVideoPlayerStandard.setUp(article.video_uri, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, article.text)
        val url= Uri.parse("http://mpic.spriteapp.cn/crop/566x360/picture/2017/0721/5971238f50189.jpg")
        jcVideoPlayerStandard.thumbImageView.setImageURI(url)

    }
}


