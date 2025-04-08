package com.smartAd.api.application.ad


import com.smartAd.api.domain.ad.model.NaverApiInfo
import com.smartAd.api.domain.ad.repository.NaverApiInfoRepository
import com.smartAd.api.interfaces.ad.dto.AddApiInfoRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime

/**
 * Role : UseCase, 시나리오 실행, 트랜잭션 제어 담당
 */
@Service
class NaverApiInfoApplicationService(
    private val naverApiInfoRepository: NaverApiInfoRepository,
) {
    @Transactional
    fun addApiInfo(request: AddApiInfoRequest) {
        val naverApiInfo = NaverApiInfo(
            customerId = request.customerId,
            accessLicense = request.accessLicense,
            secretKey = request.secretKey,
            userId = request.userId,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )

        naverApiInfoRepository.save(naverApiInfo)
    }

}