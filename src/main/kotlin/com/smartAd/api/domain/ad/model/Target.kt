package com.smartAd.api.domain.ad.model

import java.util.Date

/**
 * 타겟 정보 클래스
 */
data class Target(
    var nccTargetId: String? = null,
    var ownerId: String? = null,
    var targetTp: String? = null,
    var target: Map<String, Any>? = null,
    var regTm: Date? = null,
    var editTm: Date? = null
)