package com.smartAd.api.interfaces.auth.dto

data class LoginRequest(
    val username: String,
    val password: String,
)