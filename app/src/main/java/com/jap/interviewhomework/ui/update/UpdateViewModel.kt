package com.jap.interviewhomework.ui.update


import android.provider.Contacts.SettingsColumns.KEY
import android.util.Log
import androidx.constraintlayout.solver.widgets.analyzer.Dependency
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jap.interviewhomework.Repository.UpdateRepository
import com.jap.interviewhomework.ui.login.LogDataResult
import com.jap.interviewhomework.ui.login.LoginActivity.Companion.LOGIN_DATA

class UpdateViewModel @ViewModelInject constructor(
    private val updateRepository: UpdateRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(), LifecycleObserver {

    private val _logindata = MutableLiveData<LogDataResult>()
    val logindata: LiveData<LogDataResult> = _logindata



    fun Login_textview(){
        _logindata.postValue(savedStateHandle.get<LogDataResult>(LOGIN_DATA))
    }

}