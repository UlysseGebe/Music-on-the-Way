package com.hetic.musicontheway.recyclerView.item

import android.view.View
import com.hetic.musicontheway.R
import com.hetic.musicontheway.recyclerView.model.Event
import com.hetic.musicontheway.recyclerView.viewholder.EventViewHolder
import com.mikepenz.fastadapter.items.AbstractItem

class EventItem(val event: Event): AbstractItem<EventViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.row_event
    override val type: Int
        get() = R.id.TitltRowEvent

    override fun getViewHolder(v: View): EventViewHolder {
        return EventViewHolder(v)
    }
}