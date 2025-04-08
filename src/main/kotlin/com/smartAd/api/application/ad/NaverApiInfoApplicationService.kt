package com.smartAd.api.application.ad


import com.smartAd.api.domain.ad.model.NaverApiInfo
import com.smartAd.api.domain.ad.repository.NaverApiInfoRepository
import com.smartAd.api.interfaces.auth.dto.SignupRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Role : UseCase, 시나리오 실행, 트랜잭션 제어 담당
 */
@Service
class NaverApiInfoApplicationService(
    private val naverApiInfoRepository: NaverApiInfoRepository
) {
    @Transactional
    fun create(request: SignupRequest): NaverApiInfo {
        //todo: 해당 부분 구현
        throw IllegalArgumentException("implement me");
    }

    fun validateNaverApiInfo(username: String, password: String): NaverApiInfo {
        throw IllegalArgumentException("implement me");
    }
}