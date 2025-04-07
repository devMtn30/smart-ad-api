package com.smartAd.api.domain.ad.model


/**
 * 캠패인 활성화 여부 클래스
 */
data class UserLock(
    var nccCampaignId: String? = null,
    var userLock: Boolean? = null
)