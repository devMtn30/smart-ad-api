package com.smartAd.api.domain.ad.model


/**
 * 캠페인 노출 기간 등록, 수정 시 필요한 정보 클래스
 */
data class Period(
    var nccCampaignId: String? = null,
    var userLock: Boolean? = null,
    var periodStartDt: String? = null,
    var periodEndDt: String? = null,
    var usePeriod: Boolean? = null
)