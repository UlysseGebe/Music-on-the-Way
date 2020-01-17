package com.hetic.musicontheway.recyclerView.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class EventMap(
    val station : String,
    val lon : String,
    val lat : String

): Parcelable