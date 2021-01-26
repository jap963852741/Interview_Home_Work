package com.jap.interviewhomework.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jap.interviewhomework.R
import com.jap.interviewhomework.data.LoginRepository
import com.jap.interviewhomework.data.NewsRepository
import com.jap.interviewhomework.data.model.LoginResponse
import com.jap.interviewhomework.data.model.News
import com.jap.interviewhomework.data.model.NewsResponse
import com.jap.interviewhomework.ui.login.LoggedInUserView
import com.jap.interviewhomework.ui.login.LoginResult
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel(){
    val _News = MutableLiveData<NewsResult>()

    val News: LiveData<NewsResult> = _News

    fun news() {
        // can be launched in a separate asynchronous job
        var temp_array = arrayListOf<News>()
        val observer: Observer<NewsResponse> = object : Observer<NewsResponse> {
            override fun onNext(item: NewsResponse) {
                Log.e("TAG", "next:$item")
                val news_list = item.News
                for (news in news_list){
                    temp_array.add(news)
                }
                _News.postValue(NewsResult(success = temp_array))
            }
            override fun onError(e: Throwable) {
                println("Error Occured ${e.message}")
                _News.postValue(NewsResult(error = R.string.login_failed))
            }
            override fun onComplete() {
            }
            override fun onSubscribe(d: Disposable) {
            }
        }

        newsRepository.news()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .subscribe(observer)

    }

}