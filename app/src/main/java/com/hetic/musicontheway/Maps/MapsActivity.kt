package com.hetic.musicontheway.Maps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hetic.musicontheway.ActivityPlayerArtistefromUser
import com.hetic.musicontheway.R
import com.hetic.musicontheway.recyclerView.EventActivity
import com.hetic.musicontheway.recyclerView.item.EventItem
import com.hetic.musicontheway.recyclerView.model.Event
import com.hetic.musicontheway.recyclerView.model.EventMap
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.activity_maps.showEvents

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        showEvents.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("Events").orderByKey()

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMinZoomPreference(12.0f)
        mMap.setMaxZoomPreference(20.0f)

        /*reference.addValueEventListener(object : ValueEventListener {
            val listEvent = mutableListOf<EventMap>()
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (snapshot in dataSnapshot.children) {
                    val value = snapshot.getValue(EventMap::class.java)
                    if (value != null) {
                        listEvent.add(value)
                    }
                }
                for (element in listEvent) {
                    val lat = element.lat.toDouble()
                    val lon = element.lon.toDouble()
                    val title = element.station
                    // Add a marker in Sydney and move the camera
                    val event = LatLng(lat, lon)
                    mMap.addMarker(MarkerOptions().position(event).title(title))
                }
                Toast.makeText(this@MapsActivity, "Show", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MapsActivity, "Failed", Toast.LENGTH_SHORT).show()
            }
        })*/

        val paris = LatLng(48.866667, 2.333333)
        mMap.addMarker(MarkerOptions().position(paris).title("Paris"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(paris))
    }
}