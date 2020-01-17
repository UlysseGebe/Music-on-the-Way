package com.hetic.musicontheway

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import com.hetic.musicontheway.FireBase.Event
import com.hetic.musicontheway.network.Service
import com.hetic.musicontheway.network.data.Result
import com.hetic.musicontheway.recyclerView.EventActivity
import kotlinx.android.synthetic.main.activity_newevent.*
import kotlinx.android.synthetic.main.profil_artist_from_artist.*
import kotlinx.android.synthetic.main.profil_artist_from_artist.DateNewEvent
import kotlinx.android.synthetic.main.profil_artist_from_artist.StationNewEvent
import kotlinx.android.synthetic.main.profil_artist_from_artist.SubmitNew
import kotlinx.android.synthetic.main.profil_artist_from_artist.TimeNewEvent
import kotlinx.android.synthetic.main.profil_artist_from_artist.TitleNewEvent
import kotlinx.android.synthetic.main.profil_artist_from_artist.createEvenement
import kotlinx.android.synthetic.main.profilartistefromuser.isPlaying
import kotlinx.android.synthetic.main.profilartistefromuser.playBtn
import kotlinx.android.synthetic.main.profilartistefromuser.positionBar
import kotlinx.android.synthetic.main.profilartistefromuser.remainingTimeLabel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivityPlayerArtisteFromArtiste : AppCompatActivity() {

    private lateinit var mp : MediaPlayer
    private var totalTime : Int = 0
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("Events")

    // private lateinit var Service : Service
    lateinit var title: String
    lateinit var station: String
    lateinit var date: String
    lateinit var time: String
    lateinit var lat: String
    lateinit var lon: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_artist_from_artist)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.navitia.io/v1/coverage/fr-idf/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        buttonEvent.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }

        buttonMusic.setOnClickListener {
            val intent = Intent(this, ActivityPlayerArtistefromUser::class.java)
            startActivity(intent)
        }

        SubmitNew.setOnClickListener {
            title = TitleNewEvent.text.toString()
            station = StationNewEvent.text.toString()
            date = DateNewEvent.text.toString()
            time = TimeNewEvent.text.toString()

            if (title != null) {
                val service = retrofit.create(Service::class.java)
                service.getdatas(station).enqueue(object: Callback<Result> {
                    override fun onFailure(call: Call<Result>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<Result>, response: Response<Result>) {
                        addEvent(response.body())
                    }
                })
            } else {
                Toast.makeText(this, "Il manque des informations", Toast.LENGTH_SHORT).show()
            }
        }

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

    fun addAppears(v: View) {
        addArtistEvent.visibility = View.VISIBLE
    }

    fun addEventForArtist(v: View) {
        createEvenement.visibility = View.VISIBLE
    }

    private fun addEvent(body: Result?) {
        Toast.makeText(this, station, Toast.LENGTH_SHORT).show()

        station = body!!.places[0].stop_area.label
        lat = body!!.places[0].stop_area.coord.lat
        lon = body!!.places[0].stop_area.coord.lon

        val eventID = reference.push().key
        val event = Event(eventID, title, station, date, time, lon, lat)

        reference.child("Event$eventID").setValue(event)

        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, ActivityPlayerArtisteFromArtiste::class.java)
        startActivity(intent)
    }
}