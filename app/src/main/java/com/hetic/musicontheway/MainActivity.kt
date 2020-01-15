package com.hetic.musicontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hetic.musicontheway.FireBase.Add.NewEvent
import com.hetic.musicontheway.Maps.MapsActivity
import com.hetic.musicontheway.recyclerView.EventActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        landToArtiste.setText(getString(R.string.IAM)+"<b>"+getString(R.string.artiste)+"</b>")
        landToVoyageur.setText(getString(R.string.IAM)+"<b>"+getString(R.string.voyageur)+"</b>")


        landToArtiste.setOnClickListener {
            val intent = Intent(this, NewEvent::class.java)
            startActivity(intent)
        }

        landToVoyageur.setOnClickListener {
            /*val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)*/
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

    }
}
