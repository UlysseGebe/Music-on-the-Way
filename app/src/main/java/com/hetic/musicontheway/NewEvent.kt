package com.hetic.musicontheway

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_newevent.*

class NewEvent : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("Events")

    lateinit var name: String
    lateinit var text: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newevent)

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