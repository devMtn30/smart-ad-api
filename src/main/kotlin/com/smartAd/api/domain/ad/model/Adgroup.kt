package com.smartAd.api.domain.ad.model

import com.smartAd.api.domain.ad.AdGroupTypes
import com.smartAd.api.domain.ad.AdRollingTypes

/**
 * 그룹 클래스
 */
data class Adgroup(
    /* API 관련 Response 처리 */
    var code: Long? = null,
    var title: String? = null,
    var detail: String? = null,

    /* 키 정보 */
    var nccAdgroupId: String? = null,
    var customerId: Long? = null,
    var nccCampaignId: String? = null,
    var mobileChannelId: String? = null,
    var pcChannelId: String? = null,

    /* 유형 정보 */
    var name: String? = null,
    var adRollingType: AdRollingTypes? = null,
    var adgroupType: AdGroupTypes? = null,

    /* 타겟 정보 */
    var targets: Any? = null,  // 필요에 따라 제네릭/별도 클래스 사용 가능

    /* 입찰 정보 */
    var bidAmt: Long? = null,
    var contentsNetworkBidAmt: Long? = null,
    var useCntsNetworkBidAmt: Boolean? = null,
    var mobileNetworkBidWeight: Long? = null,
    var pcNetworkBidWeight: Long? = null,

    /* 하루예산 정보 */
    var dailyBudget: Long? = null,
    var useDailyBudget: Boolean? = null,

    /* 활성화여부, 상태, 시간정보 */
    var userLock: Boolean? = null,
    var status: String? = null,
    var statusReason: String? = null,
    var regTm: String? = null,
    var editTm: String? = null,
    var useKeywordPlus: Boolean? = null,
    var keywordPlusWeight: Int? = null
)