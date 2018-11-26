package com.nilin.happymoment.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nilin.happymoment.ui.adapter.PictureAdapter

import com.nilin.happymoment.R
import com.nilin.happymoment.bean.Contentlist
import com.nilin.happymoment.bean.Result
import com.nilin.happymoment.net.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_joke.*
import org.jetbrains.anko.support.v4.toast


class PictureFragment : Fragment() {

    var adapter: PictureAdapter? = null
    var page = 1
    var isRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_picture, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData(page, 42858, "337a9210f689433d9a45a97f037406dc")
        initRecyclerView()
    }

    fun initRecyclerView() {
        recyclerview.layoutManager = LinearLayoutManager(activity)
        adapter = PictureAdapter(activity!!.applicationContext, R.layout.item_picture)
        recyclerview.adapter = adapter
        adapter!!.setOnLoadMoreListener({ loadMore() }, recyclerview)

        swipeLayout.setOnRefreshListener({
            page = 1
            isRefresh = true
            loadData(page, 42858, "337a9210f689433d9a45a97f037406dc")
        })
    }


    protected fun loadData(page: Int, showapi_appid: Int, showapi_sign: String) {
        val api = Api.Factory.create()
        api.getPictureData(page, 42858, showapi_sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ Result ->
                    parseResult(Result)
                }, {})
    }

    private fun loadMore() {
        page++
        loadData(page, 42858, "337a9210f689433d9a45a97f037406dc")
    }

    fun parseResult(result: Result) {
        if (result.showapi_res_body.ret_code != 0) {
            loadError()
        } else {
            loadSuccess(result.showapi_res_body.contentlist)
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
}
