package com.hetic.musicontheway.network.data

class Coord(val lat : String, val lon : String)

class StopArea(val label : String, val coord : Coord)

class Places(val stop_area : StopArea)

class Result(val places : Array<Places>)