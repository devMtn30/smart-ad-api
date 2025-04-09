package com.smartAd.api.application.ad


import com.smartAd.api.domain.ad.model.NaverApiInfo
import com.smartAd.api.domain.ad.repository.NaverApiInfoRepository
import com.smartAd.api.interfaces.ad.dto.AddApiInfoRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * Role : UseCase, 시나리오 실행, 트랜잭션 제어 담당
 */
@Service
class NaverApiInfoApplicationService(
    private val naverApiInfoRepository: NaverApiInfoRepository,
) {
    @Transactional
    fun addApiInfo(request: AddApiInfoRequest, userId: Long): NaverApiInfo {
        // 유효성 검사 추가
        require(request.customerId.isNotBlank()) { "고객 ID는 필수 항목입니다." }
        require(request.accessLicense.isNotBlank()) { "접근 라이센스는 필수 항목입니다." }
        require(request.secretKey.isNotBlank()) { "시크릿 키는 필수 항목입니다." }
        
        val naverApiInfo = NaverApiInfo(
            customerId = request.customerId,
            accessLicense = request.accessLicense,
            secretKey = request.secretKey,
            userId = userId,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )

        return naverApiInfoRepository.save(naverApiInfo)
    }

    fun findUserAccount(userId: Long): List<NaverApiInfo> {
        return naverApiInfoRepository.findAllByUserId(userId)
    }

}