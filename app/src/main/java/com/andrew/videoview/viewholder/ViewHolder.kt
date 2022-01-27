package com.andrew.videoview.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.andrew.videoview.R

/**
 * @author Andrew Kim
 * @since 2021. 06. 06
 * @desc Item View Holder
 */
class ViewHolder(view: View) {
    val image: ImageView = view?.findViewById(R.id.list_image) as ImageView
    val title: TextView = view?.findViewById(R.id.list_title) as TextView
    val content: TextView = view?.findViewById(R.id.list_content) as TextView
}