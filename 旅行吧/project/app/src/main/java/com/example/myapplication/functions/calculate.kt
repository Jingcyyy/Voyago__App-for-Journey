package com.example.myapplication.functions

import android.content.Context
import com.example.myapplication.Data.attraction
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


fun getDistance(
    lat1: Double,
    lon1: Double,
    lat2: Double,
    lon2: Double
): Double {
    val earchR:Double = 6371393.0;
    val tempsin = sin(Math.toRadians(lat1-lat2)/2)*sin(Math.toRadians(lat1-lat2)/2)
    val tempcos = cos(Math.toRadians(lat1))*cos(Math.toRadians(lat2))*sin(Math.toRadians(lon1-lon2)/2)*sin(Math.toRadians(lon1-lon2)/2);
    return 2*earchR* asin(sqrt(tempsin+tempcos))/1000;
}
fun calDistance(data:List<attraction>,context: Context): MutableList<MutableList<Double>> {
    var attdistance = mutableListOf<MutableList<Double>>();
    for(i in data){
        var tempdistance = mutableListOf<Double>()
        for(j in data){
            tempdistance.add(getDistance(i.location_lat.toDouble(),i.location_lng.toDouble(),j.location_lat.toDouble(),j.location_lng.toDouble()));
        }
        attdistance.add(tempdistance);
    }
    return attdistance;
}
