package com.example.myapplication.recharge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.R


class ViewFlipperAdapter(private val context: Context, private val data: List<String>?) :
    BaseAdapter() {
    override fun getCount(): Int {
        return data?.size ?: 0
    }

    override fun getItem(position: Int): Any {
        return data?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.tvItem.text = data!![position]
        return view
    }

    internal class ViewHolder(view: View) {
        var tvItem: TextView

        init {
            tvItem = view.findViewById(R.id.tv_item)
        }
    }
}
