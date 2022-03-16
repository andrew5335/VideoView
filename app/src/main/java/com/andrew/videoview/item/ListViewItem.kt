package com.andrew.videoview.item

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

/**
 * @author Andrew Kim
 * @since 2021. 06. 06
 * @desc ListView data Class
 *       리스트 표시용 아이템
 */
data class ListViewItem(
    @SerializedName("icon") val icon: Drawable
    , @SerializedName("title") val title: String
    , @SerializedName("content") val content: String
    , @SerializedName("imageUrl") val imageUrl: String
)