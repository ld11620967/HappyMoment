package com.nilin.happymoment

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.nilin.happymoment.bean.Contentlist



class JokeAdapter(var context: Context, layoutId:Int) : BaseQuickAdapter<Contentlist, BaseViewHolder>(layoutId) {

    override fun convert(viewHolder: BaseViewHolder?, article: Contentlist?) {

            viewHolder!!.setText(R.id.title,article!!.text)

    }
}


