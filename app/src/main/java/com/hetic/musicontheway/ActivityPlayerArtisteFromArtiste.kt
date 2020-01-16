package com.hetic.musicontheway

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Html
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import com.hetic.musicontheway.FireBase.Add.NewEvent
import com.hetic.musicontheway.Maps.MapsActivity
import com.hetic.musicontheway.recyclerView.EventActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.profil_artist_from_artist.*
import kotlinx.android.synthetic.main.profilartistefromuser.*
import kotlinx.android.synthetic.main.profilartistefromuser.isPlaying
import kotlinx.android.synthetic.main.profilartistefromuser.playBtn
import kotlinx.android.synthetic.main.profilartistefromuser.positionBar
import kotlinx.android.synthetic.main.profilartistefromuser.remainingTimeLabel
import kotlin.properties.Delegates

class ActivityPlayerArtisteFromArtiste : AppCompatActivity() {

    private lateinit var mp : MediaPlayer
    private var totalTime : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_artist_from_artist)

        mp = MediaPlayer.create(this, R.raw.perfect)
        mp.isLooping=true
        totalTime=mp.duration

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
    val addArtistE = findViewById<LinearLayout>(R.id.addArtistEvent)

    fun addAppears(){
            addArtistE.setAlpha(1f)
    }

    val creationEvent = findViewById<LinearLayout>(R.id.createEvenement)

    fun addEventForArtist(){
        creationEvent.setAlpha(1f)
    }
}