package com.jap.interviewhomework.Repository

import com.jap.interviewhomework.Repository.remotedatasource.LoginResponse
import io.reactivex.rxjava3.core.Observable

/**
 * Class that requests authentication and user information from the remote Repository source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    fun login(username: String, password: String): Observable<LoginResponse> {
        return dataSource.login(username, password)
    }

}