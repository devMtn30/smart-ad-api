package com.smartAd.api.domain.ad.model

import com.smartAd.api.domain.ad.AdTypes
import com.smartAd.api.domain.ad.InspectStatus

/**
 * 소재 클래스
 */
/* API 관련 Response 처리 */
data class Ad(
    var code: Long? = null,
    var title: String? = null,
    var detail: String? = null,

    /* 키 정보 */
    var nccAdId: String? = null,
    var nccAdgroupId: String? = null,
    var customerId: Long? = null,

    /* 소재 유형 */
    var type: AdTypes? = null,
    var status: String? = null,
    var statusReason: String? = null,
    var inspectRequestMsg: String? = null,
    var inspectStatus: InspectStatus? = null,
    var userLock: Boolean? = null,
    var nccQi: Map<String, Any>? = null
    ) {
}