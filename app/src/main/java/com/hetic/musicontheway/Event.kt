package com.hetic.musicontheway

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Event(
    val eventID : String? = "" ,
    val name : String = "",
    val text : String =""
)