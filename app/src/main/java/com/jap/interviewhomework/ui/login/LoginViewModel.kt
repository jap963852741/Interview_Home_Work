package com.jap.interviewhomework.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.jap.interviewhomework.Repository.LoginRepository

import com.jap.interviewhomework.R
import com.jap.interviewhomework.Repository.bean.LoginResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val observer: Observer<LoginResponse> = object : Observer<LoginResponse> {
            override fun onNext(item: LoginResponse) {
                _loginResult.value = LoginResult(success = LoginDataResult(loginResponse = item))
            }
            override fun onError(e: Throwable) {
                _loginResult.value =  LoginResult(error = R.string.login_failed)
            }
            override fun onComplete() {
            }
            override fun onSubscribe(d: Disposable) {
            }
        }

        loginRepository.login(username, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
//            username.isNotBlank()
              false
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}