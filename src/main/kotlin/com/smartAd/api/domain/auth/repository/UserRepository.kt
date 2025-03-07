package com.smartAd.api.domain.auth.repository

import com.smartAd.api.domain.auth.model.User

interface UserRepository {
    fun save(user: User): User
    fun findByUsername(username: String): User?
}