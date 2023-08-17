package com.jiushang.mysunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jiushang.mysunnyweather.logic.Repository
import com.jiushang.mysunnyweather.logic.model.Location

class WeatherViewModel: ViewModel() {
    private val locationLiveDe = MutableLiveData<Location>()

    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveDe){
        location->
        Repository.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String){
        locationLiveDe.value = Location(lng, lat)
    }
}