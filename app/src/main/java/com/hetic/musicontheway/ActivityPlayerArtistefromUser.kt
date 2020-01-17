package com.hetic.musicontheway

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Html
import android.view.View
import android.widget.SeekBar
import com.hetic.musicontheway.FireBase.Add.NewEvent
import com.hetic.musicontheway.Maps.MapsActivity
import com.hetic.musicontheway.recyclerView.EventActivity
import com.hetic.musicontheway.recyclerView.EventDetailActivity
import com.hetic.musicontheway.recyclerView.model.Event
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.profilartistefromuser.*
import kotlin.properties.Delegates

class ActivityPlayerArtistefromUser : AppCompatActivity() {

    companion object {
        private val EVENT_DETAIL_EXTRA = "event_extra"

        fun createIntent(context: Context, event: Event): Intent {
            val intentEventDetail = Intent(context, EventDetailActivity::class.java)

            intentEventDetail.putExtra(EVENT_DETAIL_EXTRA, event)

            return intentEventDetail
        }
    }

    private lateinit var mp : MediaPlayer
    private var totalTime : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profilartistefromuser)

        val event = intent.getParcelableExtra<Event>(ActivityPlayerArtistefromUser.EVENT_DETAIL_EXTRA)

        mp = MediaPlayer.create(this, R.raw.perfect)
        mp.isLooping=true
        totalTime=mp.duration

        buttonEvent.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }

        buttonMusic.setOnClickListener {
            val intent = Intent(this, ActivityPlayerArtistefromUser::class.java)
            startActivity(intent)
        }

        //positionBar
        positionBar.max=totalTime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                    if(fromUser){
                        mp.seekTo(progress)
                    }

                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch( p0: SeekBar?) {
                }
            }
        )

        //Thread
        Thread(Runnable{
            while(mp!=null){
                try{
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e:InterruptedException){

                }
            }
        }).start()

    }

    @SuppressLint("Handler")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            //Update position bar
            positionBar.progress = currentPosition


            var remainingTime = createTimeLabel(totalTime-currentPosition)
            remainingTimeLabel.text="-$remainingTime"
        }
    }

    fun createTimeLabel(time : Int): String{
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if(sec<10) timeLabel +="0"
        timeLabel += sec

        return timeLabel
    }


    fun playBtnClick(v: View){

        if(mp.isPlaying){
            //Stop
            mp.pause()
            playBtn.setBackgroundResource(R.drawable.play)
            isPlaying.setAlpha(0f)

        }
        else{
            //Start
            mp.start()
            playBtn.setBackgroundResource(R.drawable.pause)
            isPlaying.setAlpha(1f)

        }
    }


}
