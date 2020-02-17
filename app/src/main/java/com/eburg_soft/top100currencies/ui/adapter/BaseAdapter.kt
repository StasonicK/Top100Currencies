package com.eburg_soft.top100currencies.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH : BaseAdapter.BaseViewHolder> : RecyclerView.Adapter<VH>() {
    //a list of elements
    var items : ArrayList<Any> = ArrayList()
    //return size of the list
    override fun getItemCount(): Int {
        return items.size
    }
    //binds views with content
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
    //return an element position in the list
    fun getItem(position: Int): Any {
        return items[position]
    }
    //add one element
    fun add(newItem: Any) {
        items.add(newItem)
    }
    //add all elements
    fun add(newItems: List<Any>) {
        items.addAll(newItems)
    }
    //abstract class ViewHolder
    abstract class BaseViewHolder(protected val view: View) : RecyclerView.ViewHolder(view)
    {
        abstract fun bind(item: Any)
    }
}