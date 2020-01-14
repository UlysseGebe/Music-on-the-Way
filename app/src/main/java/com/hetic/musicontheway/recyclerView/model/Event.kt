package com.hetic.musicontheway.recyclerView.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Event(
    val eventID : String? = "" ,
    val name : String = "",
    val text : String =""

): Parcelable