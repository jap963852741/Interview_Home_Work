package com.jap.interviewhomework.ui.login

import com.jap.interviewhomework.Repository.remotedatasource.LoginResponse
import java.io.Serializable

/**
 * User details post authentication that is exposed to the UI
 */
data class LoginDataResult(
        val loginResponse: LoginResponse
        //... other Repository fields that may be accessible to the UI
): Serializable