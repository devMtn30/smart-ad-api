package com.smartAd.api.domain.auth.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var roles: String = "ROLE_USER",

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
}