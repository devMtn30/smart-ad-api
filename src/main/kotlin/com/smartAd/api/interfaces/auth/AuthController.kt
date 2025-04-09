package com.smartAd.api.interfaces.auth

import com.smartAd.api.application.auth.AuthApplicationService
import com.smartAd.api.infrastructure.auth.security.CustomUserPrincipal
import com.smartAd.api.infrastructure.auth.security.TokenProvider
import com.smartAd.api.interfaces.auth.dto.LoginRequest
import com.smartAd.api.interfaces.auth.dto.LoginResponse
import com.smartAd.api.interfaces.auth.dto.SignupRequest
import com.smartAd.api.interfaces.auth.dto.SignupResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authAppService: AuthApplicationService,
    private val tokenProvider: TokenProvider
) {
    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): ResponseEntity<Any> {
        val user = authAppService.signUp(request)
        return ResponseEntity.ok(SignupResponse(user.id!!, user.username))
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        val user = authAppService.validateUser(request.username, request.password)
            ?: return ResponseEntity.badRequest().body("아이디 또는 패스워드가 틀렸습니다.")

        val token = tokenProvider.createToken(user.username, user.roles)
        return ResponseEntity.ok(LoginResponse(token))
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    fun myInfo(@AuthenticationPrincipal userPrincipal: CustomUserPrincipal): ResponseEntity<Any> {
        return ResponseEntity.ok(
            mapOf(
                "id" to userPrincipal.getId(),
                "username" to userPrincipal.username,
                "roles" to userPrincipal.authorities.map { it.authority }
            )
        )
    }
}
