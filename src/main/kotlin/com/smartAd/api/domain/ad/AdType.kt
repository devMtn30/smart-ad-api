package com.smartAd.api.domain.ad

/**
 * 그룹 유형 (WEB_SITE, CATALOG)
 */
enum class AdGroupTypes(val adGroupType: String) {
    WEB_SITE("WEB_SITE"),
    CATALOG("CATALOG");
}

/**
 * 소재 노출 방식 유형 (ROUND_ROBIN, PERFORMANCE)
 */
enum class AdRollingTypes(val adRollingType: String) {
    ROUND_ROBIN("ROUND_ROBIN"),
    PERFORMANCE("PERFORMANCE");
}

/**
 * 소재 유형 (TEXT_45, CATALOG_AD)
 */
enum class AdTypes(val adType: String) {
    TEXT_45("TEXT_45"),
    CATALOG_AD("CATALOG_AD");
}

/**
 * 캠페인 유형 (WEB_SITE, SHOPPING)
 */
enum class CampaignTypes(val campaignType: String) {
    WEB_SITE("WEB_SITE"),
    SHOPPING("SHOPPING");
}

/**
 * 예산 소진 방식 유형 (ACCELERATED, STANDARD) *
 */
enum class DeliveryMethodTypes(val deliveryMethodType: String) {
     ACCELERATED("ACCELERATED"),
    STANDARD("STANDARD");
}

/**
 * 검수 상태 상수
 */
enum class InspectStatus(val value: Int) {
    UNDER_REVIEW(10),
    APPROVED(20),
    PENDING(30);
}

/**
 * 전환 추적 모드 유형 (TRACKING_DISABLED, AUTO_TRACKING_MODE, PASS_THROUGH_SITE_MODE)
 */
enum class TrackingModeTypes(val trackingModeType: String) {
    TRACKING_DISABLED("TRACKING_DISABLED"),
    AUTO_TRACKING_MODE("AUTO_TRACKING_MODE"),
    PASS_THROUGH_SITE_MODE("PASS_THROUGH_SITE_MODE");
}