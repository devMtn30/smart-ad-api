package com.smartAd.api.domain.ad.model

import java.time.LocalDateTime

/**
 * 네이버 검색광고 '캠페인' 정보를 나타내는 도메인 엔티티.
 * - 실제 업무 규칙, 연관 관계 등을 Domain Layer에서 관리
 */
data class Campaign(
    val nccCampaignId: String,
    val campaignTp: String,
    val customerId: Long,
    val name: String,
    val userLock: Boolean = false,
    val useDailyBudget: Boolean = false,
    val dailyBudget: Long? = null,
    val deliveryMethod: String? = null,
    val usePeriod: Boolean = false,
    val periodStartDt: String? = null,
    val periodEndDt: String? = null,
    val regTm: LocalDateTime? = null,
    val editTm: LocalDateTime? = null,
    val sharedBudgetId: String? = null,
    val sharedBudgetName: String? = null,
    val sharedBudgetDeliveryMethod: String? = null,
    val sharedBudgetLock: Boolean? = null,
    val sharedBudgetExpectCost: Long? = null,
    val sharedDailyBudget: String? = null,
    val status: String? = null,
    val statusReason: String? = null,
    val trackingMode: String? = null,
    val trackingUrl: String? = null,
    val trackingUrlCustomParams: String? = null,
)