package com.smartAd.api.infrastructure.ad.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "naver_api_info")
data class NaverApiInfoEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val userId: Long,

    @Column(name = "customer_id", nullable = false)
    val customerId: String,

    @Column(name = "access_license", nullable = false)
    val accessLicense: String,

    @Column(name = "secret_key", nullable = false)
    val secretKey: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)