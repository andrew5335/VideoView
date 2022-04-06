package com.andrew.videoview.utils

import com.andrew.videoview.service.MediaService
import okhttp3.ResponseBody

class ErrorUtils {

    companion object {
        inline fun<reified T> getErrorResponse(errorBody: ResponseBody): T? {
            return  MediaService.retrofit.responseBodyConverter<T> (
                T::class.java,
                T::class.java.annotations
            ).convert(errorBody)
        }
    }
}