package com.smartAd.api.domain.ad.model

import com.smartAd.api.domain.ad.InspectStatus
import java.util.*

/**
 * 키워드 클래스
 */
data class AdKeyword(
    /* API 관련 Response 처리 */
    var code: Long? = null,
    var title: String? = null,
    var detail: String? = null,

    /* 키 정보 */
    var nccKeywordId: String? = null,
    var customerId: Long? = null,
    var nccAdgroupId: String? = null,
    var nccCampaignId: String? = null,

    /* 키워드 */
    var keyword: String? = null,

    /* PC, Mobile 연결 URL */
    var links: Map<String, Any>? = null,

    /* 입찰 정보 */
    var bidAmt: Long? = null,
    var useGroupBidAmt: Boolean? = null,

    /* 활성화 여부, 상태, 품질지수, 시간, 검토 관련 */
    var userLock: Boolean? = null,
    var status: String? = null,
    var statusReason: String? = null,
    var nccQi: NccQi? = null,
    var regTm: Date? = null,
    var editTm: Date? = null,
    var inspectStatus: InspectStatus? = null,
    var managedKeyword: ManagedKeyword? = null,
    var resultStatus: ResultStatus? = null
) {

    /**
     * 품질 지수 클래스
     */
    data class NccQi(
        var qiGrade: Int? = null
    )

    /**
     * 키워드 관련 클래스
     */
    data class ManagedKeyword(
        var keyword: String? = null,
        var isAdult: Boolean? = null,
        var isBrand: Boolean? = null,
        var isRestricted: Boolean? = null,
        var isSeason: Boolean? = null,
        var isSellProhibit: Boolean? = null,
        var isShoppingMall: Boolean? = null,
        var isLowSearchVolume: Boolean? = null,
        var pCPLMaxDepth: Int? = null,
        var regTm: Date? = null,
        var editTm: Date? = null
    )

    /**
     * 키워드 결과 클래스
     */
    data class ResultStatus(
        var code: Int? = null,
        var message: String? = null
    )
}