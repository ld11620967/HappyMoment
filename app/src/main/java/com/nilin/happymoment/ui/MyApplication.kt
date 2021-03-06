package com.nilin.happymoment.ui

import android.app.Application


/**
 * Created by liangd on 2017/8/2.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        // 因为我们程序运行后，Application是首先初始化的，如果在这里不用判断instance是否为空
        var instance: MyApplication? = null
            private set
    }
}