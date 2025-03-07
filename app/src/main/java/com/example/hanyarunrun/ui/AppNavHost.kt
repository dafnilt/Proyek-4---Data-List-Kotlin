package com.example.hanyarunrun.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hanyarunrun.viewmodel.DataViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hanyarunrun.viewmodel.ProfileViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: DataViewModel,
    profileViewModel: ProfileViewModel)
{

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("list") {
                    popUpTo("splash") { inclusive = true } // Hapus splash dari back stack
                }
            })
        }
        composable("form") {
            DataEntryScreen(navController = navController, viewModel = viewModel)
        }
        composable("list") {
            DataListScreen(navController = navController, viewModel = viewModel)
        }
        composable("profile") {
            ProfileScreen(navController = navController, viewModel = profileViewModel)
        }
        composable("rata_rata_lama_sekolah") {
            RataRataLamaSekolahScreen()
        }
        composable(
            route = "edit/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            EditScreen(navController = navController, viewModel = viewModel, dataId = id)
        }
    }
}
