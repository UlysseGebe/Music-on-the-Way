package com.hetic.musicontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.hetic.musicontheway.FireBase.Add.NewEvent
import com.hetic.musicontheway.Maps.MapsActivity
import com.hetic.musicontheway.recyclerView.EventActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private var status : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val artist = getString(R.string.IAM) +"<br>"+ "<b>" + getString(R.string.artiste) + "</b>"
        val voyage = getString(R.string.IAM) +"<br>"+ "<b>" + getString(R.string.voyageur) + "</b>"
        landToArtiste.setText(Html.fromHtml(artist))
        landToVoyageur.setText(Html.fromHtml(voyage))

        landToArtiste.setOnClickListener {
            landToArtiste.setBackgroundResource(R.drawable.artiste_dark)
            landToVoyageur.setBackgroundResource(R.drawable.voyageur)
            status = 1
        }

        landToVoyageur.setOnClickListener {
            landToVoyageur.setBackgroundResource(R.drawable.voyageur_dark)
            landToArtiste.setBackgroundResource(R.drawable.artiste)
            status = 2
        }

        choseButton.setOnClickListener {
            if (status == 1) {
                val intent = Intent(this, NewEvent::class.java)
                startActivity(intent)

            } else if (status == 2) {
                /*val intent = Intent(this, EventActivity::class.java)
                startActivity(intent)*/
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
