package com.hetic.musicontheway.network.data

class Coord(val lat : Int, lon : Int)

class StopArea(val label : String, val coord : ArrayList<Coord>)

class Places(val embedded_type : ArrayList<StopArea> )

class Result(val places : Array<Places>)