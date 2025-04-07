package com.smartAd.api.domain.ad.model

import com.smartAd.api.domain.ad.CampaignTypes
import com.smartAd.api.domain.ad.DeliveryMethodTypes
import com.smartAd.api.domain.ad.TrackingModeTypes

/**
 * 캠페인 클래스
 */
data class Campaign(
    /* API 관련 Response 처리 */
    val code: Long? = null,
    val title: String? = null,
    val detail: String? = null,

    /* 키 정보 */
    val nccCampaignId: String? = null,
    val customerId: Long? = null,

    /* 유형 */
    val campaignTp: CampaignTypes? = null,
    val deliveryMethod: DeliveryMethodTypes? = null,

    /* 하루예산 정보 */
    val useDailyBudget: Boolean? = null,
    val dailyBudget: Long? = null,
    val trackingMode: TrackingModeTypes? = null,
    val trackingUrl: String? = null,

    /* 노출기간 */
    val usePeriod: Boolean? = null,
    val periodStartDt: String? = null,
    val periodEndDt: String? = null,

    /* 활성화 여부, 이름 */
    val name: String? = null,
    val userLock: Boolean? = null
)