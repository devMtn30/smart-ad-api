package com.smartAd.api.domain.ad.model


/**
 * 캠페인 예산 등록, 수정 시 필요 정보를 담은 클래스
 */
data class Budget(
    var nccCampaignId: String? = null,
    var userLock: Boolean? = null,
    var dailyBudget: Long? = null,
    var useDailyBudget: Boolean? = null
)