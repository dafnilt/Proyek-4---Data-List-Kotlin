package com.example.hanyarunrun.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hanyarunrun.data.model.RataRataLamaSekolahResponse
import com.example.hanyarunrun.data.network.RetrofitClient
import kotlinx.coroutines.launch
import com.example.hanyarunrun.data.model.RataRataLamaSekolahWrapper

class JabarViewModel : ViewModel() {
    private val _dataSekolah = MutableLiveData<List<RataRataLamaSekolahResponse>>()
    val dataSekolah: LiveData<List<RataRataLamaSekolahResponse>> = _dataSekolah

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchDataSekolah() {
        viewModelScope.launch {
            try {
                _loading.value = true
                val result = RetrofitClient.api.getRataRataLamaSekolah()
                _dataSekolah.value = result.data // Langsung pakai result karena sudah berupa List
                _loading.value = false
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
                _loading.value = false
            }
        }
    }

    fun fetchDataSekolahById(id: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val result = RetrofitClient.api.getRataRataLamaSekolahById(id)
                _dataSekolah.value = listOf(result) // Dibungkus dalam list agar tetap konsisten
                _loading.value = false
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
                _loading.value = false
            }
        }
    }
}