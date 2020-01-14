package com.hetic.musicontheway.recyclerView.viewholder

import android.view.View
import com.hetic.musicontheway.recyclerView.item.EventItem
import com.mikepenz.fastadapter.FastAdapter
import kotlinx.android.synthetic.main.row_event.view.*

class EventViewHolder(itemView: View) : FastAdapter.ViewHolder<EventItem>(itemView) {
    override fun bindView(item: EventItem, payloads: MutableList<Any>) {
        val event = item.event

        itemView.eventRowTitleTextView.text = event.name.toString()
        itemView.eventRowNumberTextView.text = event.text.toString()
    }

    override fun unbindView(item: EventItem) {
        itemView.eventRowTitleTextView.text = null
        itemView.eventRowNumberTextView.text = null
    }
}