package com.jiushang.mysunnyweather.logic.dao

import com.google.gson.Gson
import com.jiushang.mysunnyweather.SunnyWeatherApplication
import com.jiushang.mysunnyweather.logic.model.Place
import com.jiushang.mysunnyweather.logic.network.SunnyWeatherNetwork

object PlaceDao {

    private fun sharePrehernces() = SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", 0)

    fun savePlace(place: Place){
        val editor = sharePrehernces().edit()
        editor.putString("place", Gson().toJson(place))
        editor.apply()
    }

    fun getSavePlace(): Place{
        val placeJson = sharePrehernces().getString("place", null)
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharePrehernces().contains("place")

}