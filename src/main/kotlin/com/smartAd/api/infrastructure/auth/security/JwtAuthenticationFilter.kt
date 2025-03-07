package com.smartAd.api.infrastructure.auth.security

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
    private val tokenProvider: TokenProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolveToken(request)
        if (!token.isNullOrBlank() && tokenProvider.validateToken(token)) {
            // 토큰에서 "roles" 클레임(예: "ROLE_USER,ROLE_ADMIN")을 파싱해 GrantedAuthority 리스트를 만든다.
            val roles = tokenProvider.getRoles(token)
                .split(",")
                .map { SimpleGrantedAuthority(it.trim()) }

            // principal(사용자 식별값), credential(보통 null), authorities(권한 목록)
            val authentication = UsernamePasswordAuthenticationToken(
                tokenProvider.getUsername(token),
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