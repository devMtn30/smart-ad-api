package com.smartAd.api.interfaces.ad

import com.smartAd.api.application.ad.NaverApiInfoApplicationService
import com.smartAd.api.interfaces.ad.dto.AddApiInfoRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val naverApiInfoApplicationService: NaverApiInfoApplicationService
) {
    @PostMapping("/api-info")
    fun addApiInfo(@RequestBody request: AddApiInfoRequest): ResponseEntity<Any> {
        naverApiInfoApplicationService.addApiInfo(request)
        return ResponseEntity.ok().build()
    }
}