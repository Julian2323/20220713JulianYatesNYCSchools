package com.example.a20220713julianyatesnycschools.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: SchoolInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SchoolInterface::class.java)
    }
}