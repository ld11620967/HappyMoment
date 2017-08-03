package com.nilin.happymoment

import android.content.Context
import android.text.format.DateUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.nilin.happymoment.bean.Contentlist
import java.text.SimpleDateFormat


class PictureAdapter(var context: Context, layoutId:Int) : BaseQuickAdapter<Contentlist, BaseViewHolder>(layoutId) {

    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss")

    override fun convert(viewHolder: BaseViewHolder?, article: Contentlist?) {

        viewHolder!!.setText(R.id.time, DateUtils.getRelativeTimeSpanString(sdf.parse(article!!.create_time).time))
        viewHolder.setText(R.id.title,article.text)

        val image: ImageView = viewHolder.getView<ImageView>(R.id.image)

        Glide
                .with(context)
                .load(article.image0)
                .into(image)
    }
}


