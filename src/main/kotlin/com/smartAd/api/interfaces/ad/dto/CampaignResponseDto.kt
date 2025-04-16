package com.smartAd.api.interfaces.ad.dto

import com.smartAd.api.domain.ad.model.Campaign

/**
 * 클라이언트에게 전달할 때, 도메인 모델을 직접 노출하기보다는
 * 필요한 필드만 담는 DTO
 */
data class CampaignResponseDto(
    val nccCampaignId: String,
    val campaignTp: String,
    val customerId: Long,
    val name: String,
    val userLock: Boolean,
    val useDailyBudget: Boolean,
    val dailyBudget: Long?,
    val deliveryMethod: String?,
    val usePeriod: Boolean,
    val periodStartDt: String?,
    val periodEndDt: String?,
    val status: String?,
    val statusReason: String?,
    val regTm: String?,
    val editTm: String?
) {
    companion object {
        fun fromDomain(campaign: Campaign): CampaignResponseDto {
            return CampaignResponseDto(
                nccCampaignId = campaign.nccCampaignId,
                campaignTp = campaign.campaignTp,
                customerId = campaign.customerId,
                name = campaign.name,
                userLock = campaign.userLock,
                useDailyBudget = campaign.useDailyBudget,
                dailyBudget = campaign.dailyBudget,
                deliveryMethod = campaign.deliveryMethod,
                usePeriod = campaign.usePeriod,
                periodStartDt = campaign.periodStartDt,
                periodEndDt = campaign.periodEndDt,
                status = campaign.status,
                statusReason = campaign.statusReason,
                regTm = campaign.regTm?.toString(),
                editTm = campaign.editTm?.toString()
            )
        }
    }
}