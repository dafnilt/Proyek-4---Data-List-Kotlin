package com.example.hanyarunrun.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.hanyarunrun.viewmodel.JabarViewModel
import com.example.hanyarunrun.viewmodel.ProfileViewModel

@Composable
fun IndeksPendidikanScreen(
    navController: NavHostController,
    viewModel: JabarViewModel,
) {
    val dataIndeks by viewModel.dataIndeks.observeAsState(emptyList())
    val isLoading by viewModel.loading.observeAsState(false)
    val error by viewModel.error.observeAsState("")

    LaunchedEffect(Unit) {
        viewModel.fetchDataIndeks()
    }

    val currentRoute = navController.currentDestination?.route ?: "indeks_pendidikan"

    Scaffold(
        bottomBar = {
            AnimatedBottomBar(navController = navController, currentRoute = currentRoute)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Indeks Pendidikan",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF102A43)
                )

                Text(
                    text = "Berdasarkan Kabupaten/Kota",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF334E68)
                )

                Spacer(modifier = Modifier.height(40.dp))

                if (isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFFFFB433))
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 80.dp) // Agar tidak tertutup BottomBar
                    ) {
                        items(dataIndeks) { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .shadow(6.dp, RoundedCornerShape(12.dp))
                                    .clip(RoundedCornerShape(12.dp)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFeffffd))
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Place,
                                            contentDescription = "Lokasi",
                                            tint = Color(0xFF627D98),
                                            modifier = Modifier.size(24.dp)
                                        )

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Text(
                                            text = "${item.nama_kabupaten_kota}",
                                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                                            color = Color(0xFF102A43)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Divider(color = Color(0xFFBCCCDC), thickness = 1.dp)

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column {
                                            Text(
                                                text = "#${item.kode_kabupaten_kota}",
                                                style = MaterialTheme.typography.labelLarge,
                                                color = Color(0xFF80CBC4)
                                            )
                                        }

                                        Column {
                                            Text(
                                                text = "${item.indeks_pendidikan} ${item.satuan}",
                                                style = MaterialTheme.typography.bodyLarge,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFF0B433)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "${item.tahun}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFF102A43)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

