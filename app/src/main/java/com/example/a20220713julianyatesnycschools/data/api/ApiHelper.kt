package com.example.a20220713julianyatesnycschools.data.api

class ApiHelper(private val schoolInterface: SchoolInterface) {

    suspend fun getSchools() = schoolInterface.getSchools()

    suspend fun getSat(dbn: String) = schoolInterface.getSat(dbn = dbn)
}