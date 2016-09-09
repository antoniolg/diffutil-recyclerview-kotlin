package com.antonioleiva.diffutilkotlin

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_item.view.*
import kotlin.properties.Delegates

class ContentAdapter() : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    var items: List<Content> by Delegates.observable(emptyList()) {
        prop, old, new ->
        notifyChanges(old, new)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_item))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun notifyChanges(old: List<Content>, new: List<Content>) {

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition].id == new[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition] == new[newItemPosition]
            }

            override fun getOldListSize() = old.size

            override fun getNewListSize() = new.size
        })

        diff.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(content: Content) = with(itemView) {
            image.loadUrl(content.image)
            text.text = content.text
        }
    }
}