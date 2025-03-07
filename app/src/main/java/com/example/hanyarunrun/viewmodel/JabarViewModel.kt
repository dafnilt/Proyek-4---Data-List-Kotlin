package com.example.hanyarunrun.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hanyarunrun.data.model.IndeksPendidikanResponse
import com.example.hanyarunrun.data.network.RetrofitClient
import kotlinx.coroutines.launch
import com.example.hanyarunrun.data.model.IndeksPendidikanWrapper

class JabarViewModel(application: Application) : AndroidViewModel(application) {
    private val _dataIndeks = MutableLiveData<List<IndeksPendidikanResponse>>()
    val dataIndeks: LiveData<List<IndeksPendidikanResponse>> = _dataIndeks

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchDataIndeks() {
        viewModelScope.launch {
            try {
                _loading.value = true
                val result = RetrofitClient.api.getIndeksPendidikan()
                _dataIndeks.value = result.data // Mengambil data dari wrapper
                _loading.value = false
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
                _loading.value = false
            }
        }
    }
}
