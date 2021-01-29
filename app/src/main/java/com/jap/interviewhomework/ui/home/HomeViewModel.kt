package com.jap.interviewhomework.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jap.interviewhomework.R
import com.jap.interviewhomework.Repository.NewsRepository
import com.jap.interviewhomework.Repository.bean.News
import com.jap.interviewhomework.Repository.bean.NewsResponse
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
                for (news in item.News){
                    temp_array.add(news)
                }
                _News.postValue(NewsResult(success = temp_array))
            }
            override fun onError(e: Throwable) {
                println("Error Occured ${e.message}")
                _News.postValue(NewsResult(error = R.string.news_failed))
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