package com.jap.interviewhomework.Repository.remotedatasource

data class LoginResponse (
//    @SerializedName("objectID")
    val objectId: String,
    val username: String,
    val code: String,
    val isVerifiedReportEmail: Boolean,
    val reportEmail: String,
    val createdAt: String,
    val updatedAt: String,
    val timezone: Long,
    val parameter: Long,
    val ACL: ACL,
    val sessionToken: String
)

data class ACL (
    //TODO 有可能是變數
    val WkuKfCAdGq: WkuKfCAdGq
)

data class WkuKfCAdGq (
    val read: Boolean,
    val write: Boolean
)
