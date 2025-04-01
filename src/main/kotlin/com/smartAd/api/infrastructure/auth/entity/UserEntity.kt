package com.smartAd.api.infrastructure.auth.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

/**

DB 스키마 매핑을 위한 JPA 전용 엔티티

@Table, @Column 등 DB 관련 설정들은 여기에서 관리
*/
@Entity
@Table(name = "users")
class UserEntity(
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