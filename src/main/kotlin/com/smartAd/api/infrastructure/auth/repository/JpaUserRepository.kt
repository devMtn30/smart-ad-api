package com.smartAd.api.infrastructure.auth.repository

import com.smartAd.api.domain.auth.model.User
import com.smartAd.api.domain.auth.repository.UserRepository
import com.smartAd.api.infrastructure.auth.entity.UserEntity
import com.smartAd.api.infrastructure.auth.entity.toDomain
import com.smartAd.api.infrastructure.auth.entity.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserSpringDataJpaRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
}


@Repository
class UserJpaRepository(
    private val userSpringDataJpaRepository: UserSpringDataJpaRepository
) : UserRepository {
    override fun save(user: User): User {
        val entity = user.toEntity();
        val saved = userSpringDataJpaRepository.save(entity)
        return saved.toDomain();
    }

    override fun findByUsername(username: String): User? {
        val entity = userSpringDataJpaRepository.findByUsername(username)
        return entity?.toDomain()
    }
}