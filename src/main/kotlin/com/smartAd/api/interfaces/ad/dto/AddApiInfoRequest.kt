package com.smartAd.api.interfaces.ad.dto

data class AddApiInfoRequest(
    val customerId: String,
    val accessLicense: String,
    val secretKey: String,
    val userId: Long,
) {

}
