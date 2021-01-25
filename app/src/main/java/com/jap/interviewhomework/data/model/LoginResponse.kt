package com.jap.interviewhomework.data.model



data class LoginResponse (
    val objectID: String,
    val username: String,
    val code: String,
    val isVerifiedReportEmail: Boolean,
    val reportEmail: String,
    val createdAt: String,
    val updatedAt: String,
    val timezone: Long,
    val parameter: Long,
    val acl: ACL,
    val sessionToken: String
)

data class ACL (
    val wkuKfCAdGq: WkuKfCAdGq
)

data class WkuKfCAdGq (
    val read: Boolean,
    val write: Boolean
)
