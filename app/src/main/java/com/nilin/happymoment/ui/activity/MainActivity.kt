package com.nilin.happymoment.ui.activity

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.nilin.happymoment.R

import com.nilin.happymoment.ui.adapter.MyFragmentPagerAdapter
import com.nilin.happymoment.ui.fragment.JokeFragment
import com.nilin.happymoment.ui.fragment.PictureFragment
import com.nilin.happymoment.ui.fragment.VideoFragment
import com.nilin.happymoment.ui.fragment.VoiceFragment
import kotlinx.android.synthetic.main.activity_main.*

import java.util.ArrayList
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import android.support.v4.view.ViewPager




class MainActivity : AppCompatActivity() {

    private var myFragmentPagerAdapter: MyFragmentPagerAdapter? = null
    private var listFragment: MutableList<Fragment>? = null
    private var listTitle: MutableList<String>? = null
//    var voiceFragment = VoiceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar);
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // Show toolbar return arrow.
        toolbar.setNavigationOnClickListener(View.OnClickListener { })
        initView()
    }

    /**
     * 初始化各控件
     */
    private fun initView() {

        // 将fragment装进列表中
        listFragment = ArrayList<Fragment>()
        listFragment!!.add(JokeFragment())
        listFragment!!.add(PictureFragment())
        listFragment!!.add(VoiceFragment())
        listFragment!!.add(VideoFragment())

        // 将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        listTitle = ArrayList<String>()
        listTitle!!.add("段子")
        listTitle!!.add("图片")
        listTitle!!.add("声音")
        listTitle!!.add("视频")

//         tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // 设置TabLayout的模式
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        // 为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(listTitle!![0]))
        tabLayout.addTab(tabLayout.newTab().setText(listTitle!![1]))
        tabLayout.addTab(tabLayout.newTab().setText(listTitle!![2]))
        tabLayout.addTab(tabLayout.newTab().setText(listTitle!![3]))


        myFragmentPagerAdapter = MyFragmentPagerAdapter(supportFragmentManager, listFragment, listTitle)

        viewPager!!.adapter = myFragmentPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                //当页签切换的时候，如果有播放视频，则释放资源
                JCVideoPlayer.releaseAllVideos()
//                if (voiceFragment.mp!!.isPlaying) {
//                    voiceFragment.mp!!.release()
//                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

}
