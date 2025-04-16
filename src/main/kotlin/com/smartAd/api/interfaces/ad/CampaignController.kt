package com.smartAd.api.interfaces.ad

import com.smartAd.api.application.ad.NaverApiInfoApplicationService
import com.smartAd.api.domain.ad.model.Campaign
import com.smartAd.api.infrastructure.ad.service.NaverAdApiService
import com.smartAd.api.infrastructure.auth.security.CustomUserPrincipal
import com.smartAd.api.interfaces.ad.dto.CampaignResponseDto
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/campaign")
class NaverCampaignController(
    private val naverApiInfoApplicationService: NaverApiInfoApplicationService,
    private val naverAdApiService: NaverAdApiService
) {

    /**
     * 사용자(userNo)가 선택한 API 정보를 통해 등록한 캠페인 목록을 조회하는 API
     * 호출 예:
     * GET /api/v1/campaign?apiInfoId=1
     */
    @GetMapping
    fun getCampaignsByIds(
        @AuthenticationPrincipal userPrincipal: CustomUserPrincipal,
        apiInfoId: Long
    ): List<CampaignResponseDto> {

        // 1) DB에서 사용자 ID로 NaverApiInfo 목록 조회
        val userApiInfos = naverApiInfoApplicationService.findUserAccount(userPrincipal.getId())

        // 2) apiInfoId에 해당하는 NaverApiInfo 찾기 (filter 대신 find를 사용하는 것이 깔끔)
        val targetApiInfo = userApiInfos.find { it.id == apiInfoId }
            ?: throw IllegalStateException("해당 사용자의 NaverApiInfo 가 없습니다.")

        // (예시) 실제로는 쿼리 파라미터로 넘어온 캠페인 IDs를 사용해야 한다면 별도의 @RequestParam을 받아서 split 처리
        val campaignIds = emptyList<String>()

        // 3) 실제 Naver 검색광고 API 호출 (ids가 없다면 빈 목록으로 호출)
        val campaignList: List<Campaign> = naverAdApiService.fetchCampaigns(targetApiInfo, campaignIds) ?: emptyList()

        // 4) Domain 객체(Campaign)를 DTO로 변환하여 반환
        return campaignList.map { CampaignResponseDto.fromDomain(it) }
    }
}