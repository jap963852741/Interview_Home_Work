package com.jap.interviewhomework

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jap.interviewhomework.Repository.network.UpdateDataSource
import com.jap.interviewhomework.Repository.UpdateRepository
import com.jap.interviewhomework.Repository.network.api.ApiInterface
import com.jap.interviewhomework.Repository.bean.LoginResponse
import com.jap.interviewhomework.Repository.bean.NewsResponse
import com.jap.interviewhomework.Repository.bean.UpdateResponse
import com.jap.interviewhomework.ui.update.Timezone
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import org.junit.runner.RunWith

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun login_api_unit_test() {
        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.jap.interviewhomework", appContext.packageName)

        val baseUrl = "https://watch-master-staging.herokuapp.com/api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)

        val observer: Observer<LoginResponse> = object : Observer<LoginResponse> {
            override fun onNext(item: LoginResponse) {
                Log.e("TAG", "next:$item")
            }
            override fun onError(e: Throwable) {
                println("Error Occured ${e.message}")
            }
            override fun onComplete() {
            }
            override fun onSubscribe(d: Disposable) {
            }
        }

        api.login("test2@qq.com", "test1234qq")
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .subscribe(observer)


        //等callback
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.e("finish","finish")

    }

    @Test
    fun news_api_unit_test() {
        // Context of the app under test.

        val baseUrl = "https://tcgbusfs.blob.core.windows.net/dotapp/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)

        val observer: Observer<NewsResponse> = object : Observer<NewsResponse> {
            override fun onNext(item: NewsResponse) {
                Log.e("TAG", "next:$item")
            }
            override fun onError(e: Throwable) {
                println("Error Occured ${e.message}")
            }
            override fun onComplete() {
            }
            override fun onSubscribe(d: Disposable) {
            }
        }

        api.news()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .subscribe(observer)


        //等callback
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.e("finish","finish")

    }


    @Test
    fun update_api_unit_test() {
        // Context of the app under test.

        val observer: Observer<UpdateResponse> = object : Observer<UpdateResponse> {
            override fun onNext(item: UpdateResponse) {
                Log.e("TAG", "next:$item")
            }
            override fun onError(e: Throwable) {
                println("Error Occured ${e.message}")
            }
            override fun onComplete() {
            }
            override fun onSubscribe(d: Disposable) {
            }
        }
        var timezone = Timezone(timezone = 5)
        UpdateRepository(UpdateDataSource()).update("r:08e6ce5ae1805c556440c06908626603","WkuKfCAdGq",timezone)
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .subscribe(observer)


        //等callback
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.e("finish","finish")

    }
}