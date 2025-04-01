package com.smartAd.api.infrastructure.auth.entity

import com.smartAd.api.domain.auth.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        username = this.username,
        email = this.email,
        password = this.password,
        roles = this.roles,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        username = this.username,
        email = this.email,
        password = this.password,
        roles = this.roles,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}