package com.nilin.happymoment.ui.adapter;

import android.content.Context
import android.text.format.DateUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.nilin.happymoment.bean.Contentlist
import java.text.SimpleDateFormat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.nilin.happymoment.GlideApp
import com.nilin.happymoment.R


class PictureAdapter(var context: Context, layoutId:Int) : BaseQuickAdapter<Contentlist, BaseViewHolder>(layoutId) {

    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss")

    override fun convert(viewHolder: BaseViewHolder?, article: Contentlist?) {

        viewHolder!!.setText(R.id.time, DateUtils.getRelativeTimeSpanString(sdf.parse(article!!.ct).time))
        viewHolder.setText(R.id.title,article.text)

        val image: ImageView = viewHolder.getView<ImageView>(R.id.image)

        val options = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .priority(Priority.HIGH)

        GlideApp
                .with(context)
                .load(article.img)
                .error(R.drawable.error)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .apply(options)
//                .transition(DrawableTransitionOptions.withCrossFade(50))
                .into(image)
    }
}


