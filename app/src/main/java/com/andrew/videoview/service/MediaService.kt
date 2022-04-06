package com.andrew.videoview.service

import com.andrew.videoview.serviceinterface.MediaInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MediaService {

    companion object {
        private const val apiUrl = "http://www.eye2web.co.kr/"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(this.apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mediaInterface: MediaInterface = retrofit.create(MediaInterface::class.java)
    }
}