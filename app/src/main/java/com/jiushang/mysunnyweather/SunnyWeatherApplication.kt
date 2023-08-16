package com.jiushang.mysunnyweather

import android.app.Application
import android.content.Context

class SunnyWeatherApplication: Application() {

    companion object{
        lateinit var context: Context
        const val TOKEN = "pkRkNo5Z5QhCKMDz"
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}