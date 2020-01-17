package com.hetic.musicontheway.recyclerView.viewholder

import android.view.View
import com.hetic.musicontheway.recyclerView.item.EventItem
import com.mikepenz.fastadapter.FastAdapter
import kotlinx.android.synthetic.main.profil_artist_from_artist.view.*
import kotlinx.android.synthetic.main.row_event.view.*

class EventViewHolder(itemView: View) : FastAdapter.ViewHolder<EventItem>(itemView) {
    override fun bindView(item: EventItem, payloads: MutableList<Any>) {
        val event = item.event

        itemView.TitltRowEvent.text = event.title
        itemView.StationRowEvent.text = event.station
    }

    override fun unbindView(item: EventItem) {
        itemView.TitltRowEvent.text = null
        itemView.StationRowEvent.text = null
    }
}