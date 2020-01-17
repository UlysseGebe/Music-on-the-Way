package com.hetic.musicontheway.FireBase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Event(
    val eventID : String? = "",
    val title : String = "",
    val station : String ="",
    val date: String,
    val time: String,
    val lon : String,
    val lat : String

)