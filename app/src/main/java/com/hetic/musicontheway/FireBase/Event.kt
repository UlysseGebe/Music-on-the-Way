package com.hetic.musicontheway.FireBase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Event(
    val eventID : String? = "" ,
    val name : String = "",
    val text : String ="",
    val label: String ="",
    val lat: String ="",
    val lon: String =""

)