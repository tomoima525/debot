package com.tomoima.debot.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import com.tomoima.debot.R
import com.tomoima.debot.strategy.DebotStrategy


class DebotMenuListAdapter(context: Context, objects: List<DebotStrategy>) : ArrayAdapter<DebotStrategy>(context, -1, objects) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val v: View
        val vh: ViewHolder

        if (view == null) {
            v = inflater.inflate(R.layout.row_debug_menu, parent, false)
            vh = ViewHolder(v)
            v?.tag = vh
        } else {
            v = view
            vh = view.tag as ViewHolder
        }
        vh.menuTitle.text = getItem(position)?.strategyMenuName
        return v
    }


    private class ViewHolder internal constructor(view: View) {
        internal var menuTitle = view.findViewById(R.id.debug_menu_title) as TextView

    }
}
