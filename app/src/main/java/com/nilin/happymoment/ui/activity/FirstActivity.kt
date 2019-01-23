package com.nilin.happymoment.ui.activity

import android.annotation.SuppressLint
import com.mob.grow.gui.GROWGUI
import com.mob.tools.utils.DeviceHelper
import android.os.Bundle
import com.mob.grow.gui.views.NewsPageView
import android.app.Activity


@SuppressLint("Registered")
class FirstActivity : Activity() {

    private var newsPageView: NewsPageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uid = DeviceHelper.getInstance(this).serialno
        val nickname = DeviceHelper.getInstance(this).appName
        val avatarUrl = "https://img-blog.csdnimg.cn/20190123123208967.png"
        newsPageView = GROWGUI.getNewsPageView(this, uid, nickname, avatarUrl, true)

        setContentView(newsPageView)
    }

    override fun onDestroy() {
        newsPageView!!.onDestroy()
        super.onDestroy()
    }
}