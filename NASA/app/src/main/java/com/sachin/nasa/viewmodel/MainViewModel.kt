package com.sachin.nasa.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sai.fabula.api.model.APODmodel
import com.sai.fabula.database.APODRepository
import kotlinx.coroutines.launch

class MainViewModel(private val apodRepository: APODRepository) : ViewModel() {

val _APODliveData = MutableLiveData<APODmodel>()

    val APODLiveData : LiveData<APODmodel>
        get() = _APODliveData

    private val _errorLiveData = MutableLiveData<String>()

    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    fun getAPOD(){

        viewModelScope.launch {

            apodRepository.getAPODImage().collect { result ->
                    Log.d("fdsfsdf","mydata ===="+ result.toString())
                    when (result) {

                        is APODmodel -> {
                            // Handle APODmodel
                            _APODliveData.value = result

                            _errorLiveData.value = ""
                        }
                        is String -> {

                            _errorLiveData.value = result

                            Log.d("error","${String}")
                            // Handle error message
                            // You can log or display the error message here
                        }
                        else -> {
                            _errorLiveData.value = "Something error Happend"
                            // Handle other cases if needed
                            Log.d("error","sec ${String}")
                        }
                    }
                }




        }
    }



}
