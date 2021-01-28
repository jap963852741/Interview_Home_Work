package com.jap.interviewhomework.ui.login

import java.io.Serializable

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult (
        val success: LogDataResult? = null,
        val error: Int? = null
): Serializable
