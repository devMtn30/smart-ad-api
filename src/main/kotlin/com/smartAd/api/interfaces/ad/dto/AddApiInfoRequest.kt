package com.smartAd.api.interfaces.ad.dto

import jakarta.validation.constraints.NotBlank

data class AddApiInfoRequest(
    @NotBlank(message = "고객 ID는 필수입니다")
    val customerId: String,
    @NotBlank(message = "인증 라이센스는 필수입니다")
    val accessLicense: String,
    @NotBlank(message = "비밀 키는 필수입니다")
    val secretKey: String
) {
}
