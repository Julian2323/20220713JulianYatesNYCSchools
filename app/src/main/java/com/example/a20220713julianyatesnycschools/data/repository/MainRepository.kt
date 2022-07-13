package com.example.a20220713julianyatesnycschools.data.repository

import com.example.a20220713julianyatesnycschools.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getSchools() = apiHelper.getSchools()

    suspend fun getSat(dbn: String) = apiHelper.getSat(dbn = dbn)
}