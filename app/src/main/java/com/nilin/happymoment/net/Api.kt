package com.nilin.happymoment.net

import com.nilin.happymoment.bean.Result
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by nilin on 2017/7/30.
 */
interface Api {

//    @GET("341-1?showapi_appid=42858")
//    fun getData(@Query("type") type: Int,
//                @Query("page") page: Int,
//                @Query("title") title: String,
//                @Query("showapi_sign") showapi_sign: String): Observable<Result>

    @GET("341-1?maxResult=50")
    fun getJokeData(@Query("page") page: Int,
                @Query("showapi_appid") showapi_appid: Int,
                @Query("showapi_sign") showapi_sign: String): Observable<Result>

    @GET("341-2?maxResult=50")
    fun getPictureData(@Query("page") page: Int,
                       @Query("showapi_appid") showapi_appid: Int,
                       @Query("showapi_sign") showapi_sign: String): Observable<Result>

    companion object Factory{
        fun create():Api {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://route.showapi.com/")
                    .build()
            return retrofit.create(Api::class.java)
        }
    }
}
