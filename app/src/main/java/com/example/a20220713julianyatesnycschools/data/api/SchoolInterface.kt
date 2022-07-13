package com.example.a20220713julianyatesnycschools.data.api

import com.example.a20220713julianyatesnycschools.data.model.SAT
import com.example.a20220713julianyatesnycschools.data.model.School
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolInterface {
    @GET("/resource/s3k6-pzi2.json")
    suspend fun getSchools(): Response<List<School>>

    @GET("/resource/f9bf-2cp4.json")
    suspend fun getSat(@Query("dbn") dbn: String): Response<List<SAT>>


    companion object {
        fun getSchoolList(): SchoolInterface {
            var retrofit = Retrofit.Builder()
                .baseUrl("https://data.cityofnewyork.us")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(SchoolInterface::class.java)

        }
    }
}