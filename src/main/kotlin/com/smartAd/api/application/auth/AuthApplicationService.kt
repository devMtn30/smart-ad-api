package com.smartAd.api.application.auth


import com.smartAd.api.domain.auth.model.User
import com.smartAd.api.domain.auth.repository.UserRepository
import com.smartAd.api.interfaces.auth.dto.SignupRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * Role : UseCase, 시나리오 실행, 트랜잭션 제어 담당
 */
@Service
class AuthApplicationService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun signUp(request: SignupRequest): User {
        val exist = userRepository.findByUsername(request.username)
        require(exist == null) { "이미 존재하는 username 입니다." }

        val user = User(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            roles = "ROLE_USER",
            email = request.email,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
        return userRepository.save(user)
    }

    fun validateUser(username: String, password: String): User? {
        val user = userRepository.findByUsername(username)
        require(user != null) { "존재하지 않는 username 입니다." }
        require(passwordEncoder.matches(password, user.password)) { "비밀번호가 일치하지 않습니다." }
        return user
    }

    @Transactional(readOnly = true)
    fun findUser(username: String): User {
        val user = userRepository.findByUsername(username)
        require(user != null) { "존재하지 않는 username 입니다." }
        return user
    }
}