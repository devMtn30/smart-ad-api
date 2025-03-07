package com.smartAd.api.infrastructure.auth.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class TokenProvider(
    @Value("\${jwt.secret}") private val secretKey: String,
    @Value("\${jwt.expiration}") private val validityInMs: Long
) {
    private val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun createToken(username: String, roles: String): String {
        val now = Date()
        val validity = Date(now.time + validityInMs)

        return Jwts.builder()
            .setSubject(username)
            .claim("roles", roles)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            parseClaims(token)
            true
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getUsername(token: String): String {
        return parseClaims(token).body.subject
    }

    fun getRoles(token: String): String {
        val claims = parseClaims(token).body
        return claims["roles"] as String
    }

    private fun parseClaims(token: String) =
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
}