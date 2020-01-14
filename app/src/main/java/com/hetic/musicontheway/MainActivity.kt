package com.hetic.musicontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hetic.musicontheway.FireBase.Add.NewEvent
import com.hetic.musicontheway.recyclerView.EventActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Push.setOnClickListener {
            val intent = Intent(this, NewEvent::class.java)
            startActivity(intent)
        }

        Visitor.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }

    }
}
