package com.smartAd.api.infrastructure.auth.repository

import com.smartAd.api.domain.ad.model.NaverApiInfo
import com.smartAd.api.domain.ad.repository.NaverApiInfoRepository
import com.smartAd.api.infrastructure.ad.entity.NaverApiInfoEntity
import com.smartAd.api.infrastructure.ad.entity.toDomain
import com.smartAd.api.infrastructure.ad.entity.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface NaverApiInfoSpringDataJpaRepository : JpaRepository<NaverApiInfoEntity, Long> {
    fun findByUserId(userId: Long): NaverApiInfoEntity?
}


@Repository
class NaverApiInfoJpaRepository(
    private val naverApiInfoSpringDataJpaRepository: NaverApiInfoSpringDataJpaRepository
) : NaverApiInfoRepository {
    override fun save(naverApiInfo: NaverApiInfo): NaverApiInfo {
        val entity = naverApiInfo.toEntity();
        val saved = naverApiInfoSpringDataJpaRepository.save(entity)
        return saved.toDomain();
    }

    override fun findByUserId(userId: Long): NaverApiInfo? {
        val entity = naverApiInfoSpringDataJpaRepository.findByUserId(userId)
        return entity?.toDomain()
    }
}