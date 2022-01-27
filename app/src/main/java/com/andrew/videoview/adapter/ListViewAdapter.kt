package com.andrew.videoview.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andrew.videoview.R
import com.andrew.videoview.item.ListViewItem
import com.andrew.videoview.viewholder.ViewHolder
import com.bumptech.glide.Glide

/**
 * @author Andrew Kim
 * @since 2021. 06. 06
 * @desc List Item Adapter
 */
class ListViewAdapter(private val items: MutableList<ListViewItem>): BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ListViewItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val convertView: View?
        val viewHolder: ViewHolder
        val mContext: Context = parent?.context!!

        if(view == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view_item, parent, false)
            viewHolder = ViewHolder(convertView)
            convertView.tag = viewHolder
        } else {
            convertView = view
            viewHolder = convertView.tag as ViewHolder
        }

        Glide.with(mContext).load(items[position].imageUrl).into(viewHolder.image)
        viewHolder.title.text = items[position].title
        viewHolder.content.text = items[position].content

        return convertView!!
    }
}

