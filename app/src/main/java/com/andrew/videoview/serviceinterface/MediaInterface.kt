package com.andrew.videoview.serviceinterface

import com.andrew.videoview.dto.MediaItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MediaInterface {

    @Headers("accept: application/json", "content-type: application/json")
    @GET("/videoview/medialist.php")
    fun getMediaList(
        @Query("apiKeyVal") apiKeyVal : String
        , @Query("offset") offset : Int
        , @Query("limit") limit : Int
        , @Query("q") q : String
    ): Call<MutableList<MediaItem>>
}