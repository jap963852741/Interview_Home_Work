package com.jap.interviewhomework.ui.update

import java.io.Serializable

/**
 * Authentication result : success (user details) or error message.
 */
data class UpdateResult (
        val success: UpdateDataResult? = null,
        val error: Int? = null
): Serializable
