package com.andrew.videoview.dto

import com.google.gson.annotations.SerializedName

/**
 * @author Andrew Kim
 * @since 2022. 03. 15
 * @desc MediaItem data Class
 *       Youtube 미디어 목록 검색 결과 아이템
 */
data class MediaItem(
    @SerializedName("searchKeyWord") val searchKeyWord:String    // 검색어
    , @SerializedName("videoId") val videoId:String    // 비디오 아이디
    , @SerializedName("title") val title:String    // 미디어 제목
    , @SerializedName("channelId") val channelId:String    // 채널 아이디
    , @SerializedName("description") val description:String    // 미디어 설명
    , @SerializedName("imgUrl") val imgUrl:String    // 썸네일 이미지 주소
    , @SerializedName("imgWidth") val imgWidth:Int    // 썸네일 이미지 가로 크기
    , @SerializedName("imgHeight") val imgHeight:Int    // 썸네일 이미지 세로 크기
    , @SerializedName("channelTitle") val channelTitle:String    // 채널 제목
    , @SerializedName("publishTime") val publishTime:String    // 발행 일시
    , @SerializedName("useYn") val useYn:String    // 사용 여부 (무조건 Y)
)
