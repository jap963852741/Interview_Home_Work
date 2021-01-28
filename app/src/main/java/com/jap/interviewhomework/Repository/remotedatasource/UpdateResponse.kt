package com.jap.interviewhomework.Repository.remotedatasource

data class UpdateResponse (
    val updatedAt: String,
    val role: Role
)
data class Role (
    val op: String
)
