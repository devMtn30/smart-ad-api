package com.smartAd.api.infrastructure.ad.entity

import com.smartAd.api.domain.ad.model.NaverApiInfo

fun NaverApiInfoEntity.toDomain(): NaverApiInfo {
    return NaverApiInfo(
        id = this.id,
        userId = this.userId,
        customerId = this.customerId,
        accessLicense = this.accessLicense,
        secretKey = this.secretKey,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun NaverApiInfo.toEntity(): NaverApiInfoEntity {
    return NaverApiInfoEntity(
        id = this.id,
        userId = this.userId,
        customerId = this.customerId,
        accessLicense = this.accessLicense,
        secretKey = this.secretKey,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}