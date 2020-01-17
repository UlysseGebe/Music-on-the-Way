package com.hetic.musicontheway.recyclerView

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hetic.musicontheway.Maps.MapsActivity
import com.hetic.musicontheway.R
import com.hetic.musicontheway.recyclerView.item.EventItem
import com.hetic.musicontheway.recyclerView.model.Event
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_events.*

class EventActivity : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("Events").orderByKey()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        showMaps.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        reference.addValueEventListener(object : ValueEventListener {
            val listEvent = mutableListOf<Event>()
            val listEvents = mutableListOf<Event>()
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (snapshot in dataSnapshot.children) {
                    val value = snapshot.getValue(Event::class.java)
                    if (value != null) {
                        listEvent.add(value)
                    }
                }
                for (element in listEvent) {
                    val eventID = element.eventID
                    val title = element.title
                    val station = element.station
                    listEvents.add(Event(eventID, title, station))
                }

                // Création de l'item adapter pour gérer les AbstractItem (FastAdapter)
                val itemAdapter = ItemAdapter<EventItem>()

                itemAdapter.add(listEvents.map { EventItem(it) })
                // Conversion de l'item adapter en FastAdapter pour assurer la compatibilité avec la recyclerview
                val eventsFastAdapter = FastAdapter.with(itemAdapter)
                // Configuration de l'affichage des cellules avec un LayoutManager (Vertical par défaut)
                eventsRecyclerView.layoutManager = LinearLayoutManager(this@EventActivity)
                // liaison du fast adapter à la recycler view pour afficher les cellules
                eventsRecyclerView.adapter = eventsFastAdapter

                // Gestion du clic sur le fast Adapter
                eventsFastAdapter.onClickListener = { view: View?, iAdapter: IAdapter<EventItem>, eventItem: EventItem, i: Int ->

                    val intentDetail = EventDetailActivity.createIntent(this@EventActivity, eventItem.event)
                    startActivity(intentDetail)

                    true // le clic a été intercepté
                }
                Toast.makeText(this@EventActivity, "Show", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EventActivity, "Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}