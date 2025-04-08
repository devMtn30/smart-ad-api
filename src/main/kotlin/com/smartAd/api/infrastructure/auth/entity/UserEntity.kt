package com.smartAd.api.infrastructure.auth.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "user")
data class UserEntity(
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
val id: Long? = null,

@Column(unique = true, nullable = false)
var username: String,

@Column(unique = true, nullable = false)
var email: String,

@Column(nullable = false)
var password: String,

@Column(nullable = false)
var roles: String = "ROLE_USER",

@CreatedDate
var createdAt: LocalDateTime = LocalDateTime.now(),
var updatedAt: LocalDateTime = LocalDateTime.now()
)