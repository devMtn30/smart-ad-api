package com.smartAd.api.interfaces.ad

import com.smartAd.api.application.ad.NaverApiInfoApplicationService
import com.smartAd.api.interfaces.ad.dto.AddApiInfoRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val naverApiInfoApplicationService: NaverApiInfoApplicationService
) {
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api-info")
    fun addApiInfo(@Valid @RequestBody request: AddApiInfoRequest): ResponseEntity<Any> {
        try {
            naverApiInfoApplicationService.addApiInfo(request)
            return ResponseEntity.status(HttpStatus.CREATED).build()
        } catch (e: Exception) {
            // 로깅 추가
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("error" to "API 정보 추가 중 오류가 발생했습니다: ${e.message}"))
        }
    }
}