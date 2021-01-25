package com.jap.interviewhomework.data

import android.util.Log
import com.jap.interviewhomework.data.model.LoggedInUser
import com.jap.interviewhomework.data.model.LoginRequest
import com.jap.interviewhomework.data.model.LoginResponse
import okhttp3.*
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Headers

import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")

            Log.e(username,username)
            Log.e(password,password)






            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

