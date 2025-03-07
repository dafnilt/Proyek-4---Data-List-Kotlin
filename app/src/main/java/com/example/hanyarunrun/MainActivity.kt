package com.example.hanyarunrun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.hanyarunrun.ui.AppNavHost
import com.example.hanyarunrun.ui.theme.HanyarunrunTheme
import com.example.hanyarunrun.viewmodel.DataViewModel
import com.example.hanyarunrun.viewmodel.ProfileViewModel
import java.lang.reflect.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import com.example.hanyarunrun.ui.RataRataLamaSekolahScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HanyarunrunTheme {
                // Inisialisasi NavController
                val navController = rememberNavController()
                // Inisialisasi ViewModel
                val dataViewModel: DataViewModel = viewModel()
                val profileViewModel: ProfileViewModel = viewModel()
                RataRataLamaSekolahScreen()
                // Menampilkan Navigation Host dengan parameter yang benar
                AppNavHost(
                    navController = navController,
                    viewModel = dataViewModel,
                    profileViewModel = profileViewModel
                )

            }
        }
    }
}




