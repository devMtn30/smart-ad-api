package com.smartAd.api.interfaces.ad

import com.smartAd.api.application.ad.NaverApiInfoApplicationService
import com.smartAd.api.infrastructure.auth.security.CustomUserPrincipal
import com.smartAd.api.interfaces.ad.dto.AddApiInfoRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val naverApiInfoApplicationService: NaverApiInfoApplicationService
) {
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api-info")
    fun addApiInfo(
        @Valid @RequestBody request: AddApiInfoRequest,
        @AuthenticationPrincipal userPrincipal: CustomUserPrincipal
    ): ResponseEntity<Any> {
        try {
            naverApiInfoApplicationService.addApiInfo(request, userPrincipal.getId())
            return ResponseEntity.status(HttpStatus.CREATED).build()
        } catch (e: Exception) {
catch (e: Exception) {
    // 로깅 추가
    logger.error("API 정보 추가 중 오류 발생", e)
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(mapOf("error" to "API 정보 추가 중 오류가 발생했습니다: ${e.message}"))
}
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api-info")
    fun findUserAccount(@AuthenticationPrincipal userPrincipal: CustomUserPrincipal): ResponseEntity<Any> {
        try {
            val apiInfoList = naverApiInfoApplicationService.findUserAccount(userPrincipal.getId())
            return ResponseEntity.status(HttpStatus.OK).body(apiInfoList)
        } catch (e: Exception) {
            // 로깅 추가
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("error" to "API 정보 조회 중 오류가 발생했습니다: ${e.message}"))
        }
    }
}