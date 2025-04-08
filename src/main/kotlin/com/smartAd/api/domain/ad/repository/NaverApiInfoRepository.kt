package com.smartAd.api.domain.ad.repository

import com.smartAd.api.domain.ad.model.NaverApiInfo

interface NaverApiInfoRepository {
    fun save(naverApiInfo: NaverApiInfo): NaverApiInfo
    fun findByUserId(userId: Long): NaverApiInfo?
}