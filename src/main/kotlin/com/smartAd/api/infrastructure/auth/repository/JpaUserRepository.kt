package com.smartAd.api.infrastructure.auth.repository

import com.smartAd.api.domain.auth.model.User
import com.smartAd.api.domain.auth.repository.UserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface JpaUserSpringDataRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}


@Repository
class JpaUserRepositoryImpl(
    private val jpaUserSpringDataRepository: JpaUserSpringDataRepository
) : UserRepository {
    override fun save(user: User): User {
        return jpaUserSpringDataRepository.save(user)
    }

    override fun findByUsername(username: String): User? {
        return jpaUserSpringDataRepository.findByUsername(username)
    }
}