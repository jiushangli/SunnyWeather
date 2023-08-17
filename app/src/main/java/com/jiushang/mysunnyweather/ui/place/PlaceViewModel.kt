package com.jiushang.mysunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jiushang.mysunnyweather.logic.Repository
import com.jiushang.mysunnyweather.logic.model.Place
import retrofit2.Response
import retrofit2.http.Query

class PlaceViewModel:ViewModel() {
    private val searchliveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchliveData){query->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String){
        searchliveData.value = query
    }


    fun savePlace(place: Place) = Repository.savaPlace(place)

    fun getSavedPlace() = Repository.getSavaPlace()

    fun isPlaceSaved() = Repository.isPalceSaved()
}