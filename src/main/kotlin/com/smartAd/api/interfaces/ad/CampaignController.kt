
import com.smartAd.api.application.ad.NaverApiInfoApplicationService
import com.smartAd.api.domain.ad.model.Campaign
import com.smartAd.api.interfaces.ad.dto.CampaignResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/campaigns")
class NaverCampaignController(
    private val naverApiInfoApplicationService: NaverApiInfoApplicationService,
    private val naverAdApiService: NaverAdApiService
) {

    /**
     * 사용자(userNo)가 등록한 NaverApiInfo를 이용하여
     * 'ids'로 넘겨받은 캠페인 ID 목록을 조회하는 예시 API
     *
     * 호출 예:
     * GET /api/v1/campaigns/ids?userNo=123&ids=nccCampaignId1,nccCampaignId2
     */
    @GetMapping("/ids")
    fun getCampaignsByIds(
        @RequestParam userNo: Long,
        @RequestParam ids: String
    ): List<CampaignResponseDto> {

        // 1) DB에서 사용자 ID로 NaverApiInfo 목록 조회
        val userApiInfos = naverApiInfoApplicationService.findUserAccount(userNo)
        // 여러 개가 있을 시, 우선 첫 번째를 사용 (비즈니스 정책에 따라 달라질 수 있음)
        val targetApiInfo = userApiInfos.firstOrNull()
            ?: throw IllegalStateException("해당 사용자의 NaverApiInfo가 없습니다.")

        // 2) 쿼리 파라미터로 들어온 ids를 콤마로 구분하여 List<String> 생성
        val campaignIds = ids.split(",").map { it.trim() }.filter { it.isNotEmpty() }

        // 3) 실제 Naver 검색광고 API 호출
        val campaignList: List<Campaign> = naverAdApiService.fetchCampaigns(targetApiInfo, campaignIds) ?: emptyList()

        // 4) Domain 객체(Campaign)를 DTO로 변환하여 반환
        return campaignList.map { CampaignResponseDto.fromDomain(it) }
    }
}