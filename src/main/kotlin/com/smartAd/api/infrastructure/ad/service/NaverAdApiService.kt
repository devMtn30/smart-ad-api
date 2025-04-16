package com.smartAd.api.infrastructure.ad.service

import com.smartAd.api.domain.ad.model.Campaign
import com.smartAd.api.domain.ad.model.NaverApiInfo
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import java.time.LocalDateTime

@Service
class NaverAdApiService {
    companion object {
        // 네이버 검색광고 API 고정 BASE_URL
        private const val NAVER_AD_BASE_URL = "https://api.searchad.naver.com"
    }

    private val restClient: RestClient = RestClient.builder()
        .baseUrl(NAVER_AD_BASE_URL)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    /**
     * Naver 검색광고 API의 "Campaign: list (by ids)" 호출 예시
     * GET /ncc/campaigns?ids=캠페인ID1,캠페인ID2
     *
     * @param naverApiInfo DB 등에 저장되어 있는 사용자별 Naver API 계정정보(license, secretKey, customerId 등)
     * @param campaignIds 조회할 캠페인 nccCampaignId 목록
     */
    fun fetchCampaigns(naverApiInfo: NaverApiInfo, campaignIds: List<String>): List<Campaign>? {

        // ids 파라미터에 넣을 값 (콤마로 구분)
        val joinedIds = campaignIds.joinToString(",")

        val typeRef = object : ParameterizedTypeReference<List<NaverCampaignResponse>>() {}

        // RestClient로 GET /ncc/campaigns?ids=... 호출
        val responseList: List<NaverCampaignResponse>? = restClient
            .get()
            .uri { uriBuilder ->
                uriBuilder.path("/ncc/campaigns")
                    .queryParam("ids", joinedIds)
                    .build()
            }
            // 헤더에 사용자별 accessLicense, secretKey, customerId 사용
            .header("X-Customer", naverApiInfo.customerId)             // String으로 저장돼 있다면 .toString() 제거/추가는 상황에 맞게 처리
            .header("X-API-KEY", naverApiInfo.accessLicense)
            .header("X-API-SECRET", naverApiInfo.secretKey)
            .retrieve()
            .body(typeRef)

        return responseList?.map {
            Campaign(
                nccCampaignId = it.nccCampaignId.orEmpty(),
                campaignTp = it.campaignTp.orEmpty(),
                customerId = it.customerId ?: 0L,
                name = it.name.orEmpty(),
                userLock = it.userLock ?: false,
                useDailyBudget = it.useDailyBudget ?: false,
                dailyBudget = it.dailyBudget,
                deliveryMethod = it.deliveryMethod,
                usePeriod = it.usePeriod ?: false,
                periodStartDt = it.periodStartDt,
                periodEndDt = it.periodEndDt,
                regTm = it.regTm?.let { dt -> LocalDateTime.parse(dt) },
                editTm = it.editTm?.let { dt -> LocalDateTime.parse(dt) },
                sharedBudgetId = it.sharedBudgetId,
                sharedBudgetName = it.sharedBudgetName,
                sharedBudgetDeliveryMethod = it.sharedBudgetDeliveryMethod,
                sharedBudgetLock = it.sharedBudgetLock,
                sharedBudgetExpectCost = it.sharedBudgetExpectCost,
                sharedDailyBudget = it.sharedDailyBudget,
                status = it.status,
                statusReason = it.statusReason,
                trackingMode = it.trackingMode,
                trackingUrl = it.trackingUrl,
                trackingUrlCustomParams = it.trackingUrlCustomParams
            )
        }
    }
}

/**
 * 네이버 캠페인 API 응답용 DTO
 */
data class NaverCampaignResponse(
    val nccCampaignId: String?,
    val campaignTp: String?,
    val customerId: Long?,
    val name: String?,
    val userLock: Boolean?,
    val useDailyBudget: Boolean?,
    val dailyBudget: Long?,
    val deliveryMethod: String?,
    val usePeriod: Boolean?,
    val periodStartDt: String?,
    val periodEndDt: String?,
    val regTm: String?,
    val editTm: String?,
    val sharedBudgetId: String?,
    val sharedBudgetName: String?,
    val sharedBudgetDeliveryMethod: String?,
    val sharedBudgetLock: Boolean?,
    val sharedBudgetExpectCost: Long?,
    val sharedDailyBudget: String?,
    val status: String?,
    val statusReason: String?,
    val trackingMode: String?,
    val trackingUrl: String?,
    val trackingUrlCustomParams: String?,
)
