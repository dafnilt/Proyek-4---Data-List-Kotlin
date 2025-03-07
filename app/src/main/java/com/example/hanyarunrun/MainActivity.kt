package com.example.hanyarunrun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.hanyarunrun.ui.AppNavHost
import com.example.hanyarunrun.ui.theme.HanyarunrunTheme
import com.example.hanyarunrun.viewmodel.DataViewModel
import com.example.hanyarunrun.viewmodel.ProfileViewModel
import com.example.hanyarunrun.ui.IndeksPendidikanScreen
import com.example.hanyarunrun.viewmodel.JabarViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HanyarunrunTheme {
                val navController = rememberNavController()
                val dataViewModel: DataViewModel = viewModel()
                val profileViewModel: ProfileViewModel = viewModel()
                val indeksViewModel: JabarViewModel = viewModel()

                AppNavHost(
                    navController = navController,
                    viewModel = dataViewModel,
                    profileViewModel = profileViewModel,
                    indeksViewModel = indeksViewModel
                )

            }
        }
    }
}




