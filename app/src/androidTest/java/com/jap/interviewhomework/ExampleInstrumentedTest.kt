package com.jap.interviewhomework

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jap.interviewhomework.data.model.ApiInterface
import com.jap.interviewhomework.data.model.LoginResponse
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
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
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(ApiInterface::class.java)
        api.login("test2@qq.com", "test1234qq").enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("onFailure",t.message.toString())
            }
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.e("onResponse","onResponse")
                if(response.code() == 200){ //登入成功
                    val result = response.body()!!.toString()
                    Log.e("result",result)
                }else{ //登入失敗
                    val code = response.code().toString()
                    Log.e("code",code)
                }

            }

        })

        //等callback
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.e("finish","finish")

    }


}