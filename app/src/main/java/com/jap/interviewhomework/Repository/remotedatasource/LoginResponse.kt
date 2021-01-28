package com.jap.interviewhomework.Repository.remotedatasource

import java.io.Serializable

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
): Serializable

data class ACL (
    //TODO 有可能是變數
    val WkuKfCAdGq: WkuKfCAdGq
): Serializable

data class WkuKfCAdGq (
    val read: Boolean,
    val write: Boolean
): Serializable
