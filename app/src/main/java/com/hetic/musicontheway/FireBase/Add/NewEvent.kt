package com.hetic.musicontheway.FireBase.Add

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newevent)

        InfosNewEvent.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
        })

        ButtonNewEvent.setOnClickListener {
            name = NameNewEvent.text.toString()
            text = InfosNewEvent.text.toString()

            if (name != null) {
                addEvent()
            } else {
                Toast.makeText(this, "Il manque des informations", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addEvent() {
        val eventID = reference.push().key
        val event = Event(eventID, name, text)

        reference.child("Event$eventID").setValue(event)

        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}