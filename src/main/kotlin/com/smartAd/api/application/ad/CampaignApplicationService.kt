package com.smartAd.api.application.ad
import com.smartAd.api.domain.ad.model.Campaign
import com.smartAd.api.infrastructure.ad.service.NaverAdApiService
import com.smartAd.api.interfaces.ad.dto.CampaignResponseDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * “캠페인 조회” 등 UseCase 단위의 시나리오를 수행하는 서비스.
 * - 트랜잭션 경계 설정
 * - Domain Service 및 Repository 호출
 * - 외부 API(NaverAdApiService) 등의 연계
 */
@Service
@Transactional(readOnly = true)
class CampaignApplicationService(
    private val naverAdApiService: NaverAdApiService
) {

    /**
     * 네이버 검색광고 API를 통해 캠페인을 조회하고,
     * 필요한 형태(DTO)로 변환하여 반환
     */
    fun getCampaigns(
        customerId: Long,
        campaignType: String?,
        baseSearchId: String?,
        recordSize: Int?,
        selector: String?
    ): List<CampaignResponseDto> {

        // 외부 API 호출
        val campaigns: List<Campaign>? = try {
            naverAdApiService.fetchCampaigns(
                customerId = customerId,
                campaignType = campaignType,
                baseSearchId = baseSearchId,
                recordSize = recordSize,
                selector = selector
            )
        } catch (e: Exception) {
            // 로깅
            logger.error("네이버 광고 API 호출 중 오류 발생", e)
            // 적절한 예외로 변환하여 상위로 전파하거나, 빈 리스트 반환
            emptyList()
        }

        // 도메인 객체 -> DTO 변환 (null이면 빈 리스트 반환)
        return campaigns?.map {
            CampaignResponseDto(
                nccCampaignId = it.nccCampaignId,
                campaignTp = it.campaignTp,
                customerId = it.customerId,
                name = it.name,
                userLock = it.userLock,
                status = it.status
            )
        } ?: emptyList()
    }
}