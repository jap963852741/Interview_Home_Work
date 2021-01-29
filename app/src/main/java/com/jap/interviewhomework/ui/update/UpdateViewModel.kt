package com.jap.interviewhomework.ui.update


import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jap.interviewhomework.R
import com.jap.interviewhomework.Repository.network.UpdateDataSource
import com.jap.interviewhomework.Repository.UpdateRepository
import com.jap.interviewhomework.Repository.bean.UpdateResponse
import com.jap.interviewhomework.ui.login.LoginDataResult
import com.jap.interviewhomework.ui.login.LoginActivity.Companion.LOGIN_DATA
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class UpdateViewModel @ViewModelInject constructor(
    private val updateRepository: UpdateRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(), LifecycleObserver {

    private val _logindata = MutableLiveData<LoginDataResult>()
    val logindata: LiveData<LoginDataResult> = _logindata

    private val _updateResult = MutableLiveData<UpdateResult>()
    val updateResult: LiveData<UpdateResult> = _updateResult


    fun LoginData_textview(){
        _logindata.postValue(savedStateHandle.get<LoginDataResult>(LOGIN_DATA))
    }

    fun updata(sessionToken :String, objectId :String, timezone : Timezone) {
        val observer: Observer<UpdateResponse> = object : Observer<UpdateResponse> {
            override fun onNext(item: UpdateResponse) {
                _updateResult.value = UpdateResult(success = UpdateDataResult(updateResponse = item))
            }
            override fun onError(e: Throwable) {
                _updateResult.value = UpdateResult(error = R.string.update_failed)
            }
            override fun onComplete() {
            }
            override fun onSubscribe(d: Disposable) {
            }
        }

        UpdateRepository(UpdateDataSource()).update(sessionToken,objectId,timezone)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(Schedulers.io())
//            .subscribe(observer)

    }

}