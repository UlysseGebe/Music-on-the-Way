package com.hetic.musicontheway.FireBase.Add

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.hetic.musicontheway.FireBase.Event
import com.hetic.musicontheway.MainActivity
import com.hetic.musicontheway.R
import com.hetic.musicontheway.network.Service
import com.hetic.musicontheway.network.data.Result
import kotlinx.android.synthetic.main.activity_newevent.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewEvent : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("Events")

    // private lateinit var Service : Service
    lateinit var name: String
    lateinit var text: String
    lateinit var lat: String
    lateinit var lon: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newevent)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.navitia.io/v1/coverage/fr-idf/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        ButtonNewEvent.setOnClickListener {
            name = NameNewEvent.text.toString()

            val service = retrofit.create(Service::class.java)
            service.getdatas(InfosNewEvent.text.toString()).enqueue(object: Callback<Result> {
                override fun onFailure(call: Call<Result>, t: Throwable) {
                }

                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    if (name != null) {
                        addEvent(response.body())
                    } else {
                        Toast.makeText(this@NewEvent, "Il manque des informations", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    fun addEvent(body: Result?) {
        text = "ok"
        val label = body!!.places[0].stop_area.label
        val lat = body!!.places[0].stop_area.coord.lat
        val lon = body!!.places[0].stop_area.coord.lon

        val eventID = reference.push().key
        val event = Event(eventID, name, text, label, lat, lon)

        reference.child("Event$eventID").setValue(event)

        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}