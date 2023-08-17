package com.jiushang.mysunnyweather.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.jiushang.mysunnyweather.logic.dao.PlaceDao
import com.jiushang.mysunnyweather.logic.model.Place
import com.jiushang.mysunnyweather.logic.model.Weather
import com.jiushang.mysunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import okhttp3.Dispatcher
import retrofit2.http.Query
import java.lang.RuntimeException
import kotlin.Exception
import kotlin.io.path.createTempDirectory

object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try{
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if(placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException(
                    "response status is ${placeResponse.status}$"
                ))
            }
        }catch (e: Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

    fun refreshWeather(lng: String, lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            coroutineScope {

                val deferredDaily = async {
                    SunnyWeatherNetwork.getDailyWeather(lng, lat)
                }

                val deferredRealtime = async {
                    SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
                }

                val realtimeResponse = deferredRealtime.await()

                val dailyResponse = deferredDaily.await()

                Log.d("jiushang", realtimeResponse.toString())
                Log.d("jiushang", dailyResponse.toString())

                if(realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                    Log.d("jiushang", "this")
                    val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(
                        RuntimeException(
                            "${realtimeResponse.status}$+${dailyResponse.status}$"
                        )
                    )
                }

            }
        }catch (e: Exception){
            Result.failure(e)
        }
        emit(result)
    }

    fun savaPlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavaPlace() = PlaceDao.getSavePlace()

    fun isPalceSaved() = PlaceDao.isPlaceSaved()

}