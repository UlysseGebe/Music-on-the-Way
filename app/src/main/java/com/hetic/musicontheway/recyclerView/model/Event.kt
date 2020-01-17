package com.hetic.musicontheway.recyclerView.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Event(
    val eventID : String? = "",
    val title : String = "",
    val station : String =""

): Parcelable