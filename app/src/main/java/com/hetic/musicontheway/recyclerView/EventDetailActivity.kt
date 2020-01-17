package com.hetic.musicontheway.recyclerView

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hetic.musicontheway.ActivityPlayerArtistefromUser
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
        val intent = Intent(this, ActivityPlayerArtistefromUser::class.java)
        startActivity(intent)
    }
}