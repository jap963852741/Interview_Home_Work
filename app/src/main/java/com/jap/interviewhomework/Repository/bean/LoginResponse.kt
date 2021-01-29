package com.jap.interviewhomework.Repository.bean

import java.io.Serializable

data class LoginResponse (
    val objectId: String,
    val username: String,
    val code: String,
    val isVerifiedReportEmail: Boolean,
    val reportEmail: String,
    val createdAt: String,
    val updatedAt: String,
    val timezone: Long,
    val parameter: Long,
    val ACL: Any,
    val sessionToken: String
): Serializable