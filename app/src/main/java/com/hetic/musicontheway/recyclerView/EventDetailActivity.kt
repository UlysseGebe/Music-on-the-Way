package com.hetic.musicontheway.recyclerView

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hetic.musicontheway.R
import com.hetic.musicontheway.recyclerView.model.Event
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity() {

    companion object {
        private val EVENT_DETAIL_EXTRA = "event_extra"

        fun createIntent(context: Context, event: Event): Intent {
            val intentEventDetail = Intent(context, EventDetailActivity::class.java)

            intentEventDetail.putExtra(EVENT_DETAIL_EXTRA, event)

            return intentEventDetail
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        val event = intent.getParcelableExtra<Event>(EVENT_DETAIL_EXTRA)

        this.title = event.eventID
        eventDetailNumberTextView.text = event.name.toString()
    }
}