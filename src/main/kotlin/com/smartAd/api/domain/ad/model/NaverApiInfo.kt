package com.smartAd.api.domain.ad.model

import java.time.LocalDateTime

data class NaverApiInfo(
    val id: Long? = null,
    val userId: Long,
    val customerId: String,
    val accessLicense: String,
    val secretKey: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)