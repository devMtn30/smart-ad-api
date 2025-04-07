package com.smartAd.api.domain.ad.model


/**
 * PC, MOBILE 링크 클래스
 */
data class Link(
    var pc: MutableMap<String, String> = HashMap(),
    var mobile: MutableMap<String, String> = HashMap()
) {
    constructor(pcUrl: String, mobileUrl: String) : this() {
        pc["final"] = pcUrl
        mobile["final"] = mobileUrl
    }
}