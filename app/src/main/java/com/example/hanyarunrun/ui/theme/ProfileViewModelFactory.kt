package com.example.hanyarunrun.ui.theme

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hanyarunrun.viewmodel.ProfileViewModel

class ProfileViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
