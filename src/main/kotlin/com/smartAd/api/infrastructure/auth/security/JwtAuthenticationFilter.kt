package com.smartAd.api.infrastructure.auth.security

import com.smartAd.api.application.auth.AuthApplicationService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val tokenProvider: TokenProvider,
    private val authApplicationService: AuthApplicationService
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = resolveToken(request)
        if (!token.isNullOrBlank() && tokenProvider.validateToken(token)) {
            val username = tokenProvider.getUsername(token)
            val roles = tokenProvider.getRoles(token)  // 예: "ROLE_USER,ROLE_ADMIN"
                .split(",")
                .map { SimpleGrantedAuthority(it.trim()) }

            val findUser = authApplicationService.findUser(username)
            if (findUser != null) {
                val customPrincipal = CustomUserPrincipal(
                    id = findUser.id ?: 0,
                    username = findUser.username,
                    password = "N/A",        // JWT 인증의 경우 비밀번호가 필요 없으므로 dummy
                    authorities = roles
                )
                val authentication = UsernamePasswordAuthenticationToken(
                    customPrincipal,
                    null, 
                    roles
                )
                SecurityContextHolder.getContext().authentication = authentication
            } else {
                logger.warn("JWT 토큰에서 가져온 사용자($username)를 찾을 수 없습니다.")
            }

            val authentication = UsernamePasswordAuthenticationToken(
                customPrincipal,
                null,
                roles
            )
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader("Authorization") ?: return null
        return if (bearer.startsWith("Bearer ")) bearer.substring(7) else null
    }
}