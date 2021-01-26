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
import com.jap.interviewhomework.data.model.NewsResponse
import com.jap.interviewhomework.ui.login.LoggedInUserView
import com.jap.interviewhomework.ui.login.LoginResult
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel(){
    val _News = MutableLiveData<ArrayList<String>>()

    val News: LiveData<ArrayList<String>> = _News

    fun news() {
        // can be launched in a separate asynchronous job
        val observer: Observer<NewsResponse> = object : Observer<NewsResponse> {
            override fun onNext(item: NewsResponse) {
                Log.e("TAG", "next:$item")
//                _loginResult.postValue(LoginResult(success = LoggedInUserView(displayName = item.toString())))
            }
            override fun onError(e: Throwable) {
                println("Error Occured ${e.message}")
//                _loginResult.postValue(LoginResult(error = R.string.login_failed))
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