package com.hetic.musicontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.hetic.musicontheway.FireBase.Add.NewEvent
import com.hetic.musicontheway.Maps.MapsActivity
import com.hetic.musicontheway.network.Service
import com.hetic.musicontheway.network.data.Result
import com.hetic.musicontheway.recyclerView.EventActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var status : Int = 0
    private lateinit var service : Service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://c655c7f8-756a-48a1-b63b-04df7cabfcfd@api.navitia.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(Service::class.java)
        service.getDatas("nation").enqueue(object: Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                t.toString()
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                response.body()
            }

        })

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
