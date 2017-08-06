package com.nilin.happymoment.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter

import com.nilin.happymoment.R
import com.nilin.happymoment.VoiceAdapter
import com.nilin.happymoment.bean.Contentlist
import com.nilin.happymoment.bean.Result
import com.nilin.happymoment.net.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_voice.*
import org.jetbrains.anko.support.v4.toast
import android.media.MediaPlayer
import android.net.Uri
import java.util.concurrent.Executors


class VoiceFragment : Fragment() {

    var adapter: VoiceAdapter? = null
    var page = 1
    var isRefresh = false
    var mp: MediaPlayer? = null
    var isPlaying:Boolean=false

    //创建一个单实例的线程,用于更新音乐信息
    private val es = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_voice, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData(31, 1, "", "337a9210f689433d9a45a97f037406dc")
        initRecyclerView()
    }

    fun initRecyclerView() {
        recyclerview.layoutManager = LinearLayoutManager(activity)
        adapter = VoiceAdapter(activity!!.applicationContext, R.layout.item_voice)
        recyclerview.adapter = adapter
        adapter!!.setOnLoadMoreListener({ loadMore() }, recyclerview)

        adapter!!.setOnItemChildClickListener(BaseQuickAdapter.OnItemChildClickListener {
            adapter, view, position ->
            val contentlist=adapter.data[position] as Contentlist
            if (isPlaying) {
                mp!!.pause()
                isPlaying=false
            } else {
                val playUri = Uri.parse(contentlist.voice_uri)
                mp= MediaPlayer.create(context, playUri)
                mp!!.start()
//                play.(R.drawable.stop)
                isPlaying=true
            }
//            es.execute(updateSteatusRunnable);//更新进度值
        })


//        fab.onClick {
//            recyclerview.smoothScrollToPosition(0)
//        }

        swipeLayout.setOnRefreshListener({
            page = 1
            isRefresh = true
            loadData(31, page, "", "337a9210f689433d9a45a97f037406dc")
        })
    }

    protected fun loadData(type: Int, page: Int, title: String, showapi_sign: String) {
        val api = Api.Factory.create()
        api.getData(type, page, title, showapi_sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Result ->
                    parseResult(Result)
                }, {})
    }

    private fun loadMore() {
        page++
        loadData(31, page, "", "337a9210f689433d9a45a97f037406dc")
    }

    fun parseResult(result: Result) {
        if (result.showapi_res_body.ret_code != 0) {
            loadError()
        } else {
            loadSuccess(result.showapi_res_body.pagebean.contentlist)
        }
        loadFinish()
    }

    fun loadError() {
        toast("读取失败！")
    }

    fun loadSuccess(data: List<Contentlist>) {
        setUp(data)
    }

    private fun setUp(data: List<Contentlist>) {
        if (isRefresh) {
            adapter!!.setNewData(data)
            isRefresh = false
        } else {
            adapter!!.addData(data)
        }
    }

    fun loadFinish() {
        if (swipeLayout!!.isRefreshing) {
            swipeLayout!!.isRefreshing = false
        }
        adapter!!.loadMoreComplete()
    }

    override fun onPause() {
        super.onPause()
        mp!!.release()
    }

}

