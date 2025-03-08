package com.example.hanyarunrun.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.*
import com.example.hanyarunrun.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))
    val progress by animateLottieCompositionAsState(composition)

    LaunchedEffect(progress) {
        if (progress == 1f) { // Jika animasi selesai
            delay(700) // Memberikan sedikit jeda
            onTimeout()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(200.dp) // Sesuaikan ukuran animasi
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Dafni Lanahtadya", style = MaterialTheme.typography.headlineMedium, color = Color.Black)
            Text("231511008", style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        }
    }
}
