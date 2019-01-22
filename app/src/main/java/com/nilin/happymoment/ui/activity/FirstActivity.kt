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

        val uid = "10" + DeviceHelper.getInstance(this).serialno
        val nickname = DeviceHelper.getInstance(this).appName + " test"
        val avatarUrl = "http://download.sdk.mob.com/2018/02/06/12/1517890066763/2000_2000_79.49.jpg"
        newsPageView = GROWGUI.getNewsPageView(this, uid, nickname, avatarUrl, true)

        setContentView(newsPageView)
    }

    override fun onDestroy() {
        newsPageView!!.onDestroy()
        super.onDestroy()
    }
}