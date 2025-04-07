package com.smartAd.api.domain.ad.model

/**
 * 헤드라인, 설명, 랜딩 URL - 파워 링크 소재 클래스
 */
data class TextNdescription(
    var pc: MutableMap<String, String> = HashMap(),
    var mobile: MutableMap<String, String> = HashMap(),
    var headline: String,
    var description: String
) {
    constructor(
        pcUrl: String,
        mobileUrl: String,
        headline: String,
        description: String
    ) : this(headline = headline, description = description) {
        pc["final"] = pcUrl
        mobile["final"] = mobileUrl
    }
}