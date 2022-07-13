package com.example.a20220713julianyatesnycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a20220713julianyatesnycschools.data.api.SchoolInterface
import com.example.a20220713julianyatesnycschools.data.model.SAT
import com.example.a20220713julianyatesnycschools.data.model.School
import com.example.a20220713julianyatesnycschools.data.repository.MainRepository
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class SchoolViewModel( val mainRepository: MainRepository): ViewModel() {

    val allSchools: MutableLiveData<List<School>> by lazy {
        MutableLiveData<List<School>>().also {
            loadSchools()
        }
    }

    val satScores: MutableLiveData<List<SAT>> by lazy {
        MutableLiveData<List<SAT>>()
    }
    var job: Job? = null
    val errorMessage = MutableLiveData<String>()

    fun getSchools(): LiveData<List<School>> {
        return allSchools
    }

    fun getSatScores(dbn: String): LiveData<List<SAT>> {
        loadSat(dbn)
        return satScores
    }

    private fun loadSchools(){
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getSchools()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    allSchools.postValue(response.body())
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }

    }

    private fun loadSat(dbn: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getSat(dbn)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    satScores.postValue(response.body())
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
    }

}



class SchoolViewModelFactory( val mainRepository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SchoolViewModel::class.java)) {
            @Suppress("UNHCHECKED_CAST")
            return SchoolViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")

    }

}