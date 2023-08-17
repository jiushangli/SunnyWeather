package com.jiushang.mysunnyweather.logic.network

import com.jiushang.mysunnyweather.SunnyWeatherApplication
import com.jiushang.mysunnyweather.logic.model.DailyResponse
import com.jiushang.mysunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    //获取实时天气
    @GET("v2.6/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng")lng: String, @Path("lat")lat: String): Call<RealtimeResponse>
    //获取未来天气
    @GET("v2.6/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng")lng: String, @Path("lat")lat: String): Call<DailyResponse>

}