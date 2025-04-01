package com.smartAd.api.domain.auth.model

import java.time.LocalDateTime

class User(
    val id: Long? = null,
    var username: String,
    var email: String,
    var password: String,
    var roles: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
) {
}