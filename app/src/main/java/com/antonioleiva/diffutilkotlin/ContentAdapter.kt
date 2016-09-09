package com.antonioleiva.diffutilkotlin

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_item.view.*
import kotlin.properties.Delegates

class ContentAdapter() : RecyclerView.Adapter<ContentAdapter.ViewHolder>(), AutoUpdatableAdapter {

    var items: List<Content> by Delegates.observable(emptyList()) {
        prop, old, new ->
        autoNotify(old, new) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_item))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(content: Content) = with(itemView) {
            image.loadUrl(content.image)
            text.text = content.text
        }
    }
}