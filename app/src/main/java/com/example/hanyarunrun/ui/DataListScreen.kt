package com.example.hanyarunrun.ui

import android.widget.Button
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.compose.runtime.livedata.observeAsState
import com.example.hanyarunrun.viewmodel.DataViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hanyarunrun.data.DataEntity
import com.example.hanyarunrun.ui.components.JetsnackButton
import com.example.hanyarunrun.ui.themejetsnack.JetsnackTheme
data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DataListScreen(navController: NavHostController, viewModel: DataViewModel) {
    val dataList by viewModel.dataList.observeAsState(emptyList())
    val totalData = dataList.size // Menghitung jumlah total data
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    Scaffold(
        bottomBar = {
            AnimatedBottomBar(navController, currentRoute)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("form") },
                containerColor = Color(0xFFFFB433),
                contentColor = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Data")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFecfffd))
                .padding(paddingValues)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color(0xFF80CBC4),
                    radius = 800f,
                    center = Offset(size.width * 0.5f, size.height * 0.01f)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "Selamat Pagi!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
                Text(
                    text = "Dafni Lanahtadya",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(22.dp))

                // Tambahkan Card untuk menampilkan total data
                Card(
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total Data",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$totalData",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF9800) // Warna teks total data
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                AnimatedContent(
                    targetState = dataList.isEmpty(),
                    transitionSpec = {
                        fadeIn(animationSpec = tween(300)) with fadeOut(animationSpec = tween(300))
                    },
                    label = "DataListAnimation"
                ) { isEmpty ->
                    if (isEmpty) {
                        EmptyDataView()
                    } else {
                        DataListView(navController, dataList, viewModel)
                    }
                }
            }
        }
    }
}



@Composable
fun AnimatedBottomBar(navController: NavHostController, currentRoute: String) {
    NavigationBar(
        containerColor = Color.White, // Latar belakang putih
        contentColor = Color.Gray // Warna default teks dan ikon
    ) {
        val items = listOf(
            BottomNavItem("Home", Icons.Default.Home, "list"),
            BottomNavItem("Data", Icons.AutoMirrored.Filled.List, "indeks"),
            BottomNavItem("Profile", Icons.Default.Person, "profile")
        )
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                icon = {
                    AnimatedContent(targetState = isSelected, label = "IconAnimation") { selected ->
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = if (selected) Color(0xFFFF9800) else Color.Gray // Oranye jika dipilih, abu-abu jika tidak
                        )
                    }
                },
                label = {
                    AnimatedVisibility(visible = isSelected) {
                        Text(
                            text = item.label,
                            color = if (isSelected) Color(0xFFFF9800) else Color.Gray // Oranye jika dipilih, abu-abu jika tidak
                        )
                    }
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors( // Atur warna agar tidak ada efek ungu
                    selectedIconColor = Color(0xFFFF9800), // Warna ikon saat dipilih
                    unselectedIconColor = Color.Gray, // Warna ikon saat tidak dipilih
                    selectedTextColor = Color(0xFFFF9800), // Warna teks saat dipilih
                    unselectedTextColor = Color.Gray, // Warna teks saat tidak dipilih
                    indicatorColor = Color.Transparent
            )
            )
        }
    }
}


@Composable
fun EmptyDataView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No Data Available", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun DataListView(navController: NavHostController, dataList: List<DataEntity>, viewModel: DataViewModel) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(dataList) { item ->
            DataItemCard(navController, item, viewModel)
        }
    }
}

@Composable
fun DataItemCard(navController: NavHostController, item: DataEntity, viewModel: DataViewModel) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ikon Data
            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = "Ikon Data",
                tint = Color(0xFF00796B),
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 12.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${item.namaProvinsi} (${item.kodeProvinsi})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${item.namaKabupatenKota} (${item.kodeKabupatenKota})",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Total: ${item.total} ${item.satuan}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tahun: ${item.tahun}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Tombol Edit & Delete dalam Column agar sejajar
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { navController.navigate("edit/${item.id}") }) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Edit Data",
                        tint = Color(0xFFFFB433)
                    )
                }
                IconButton(onClick = { viewModel.deleteData(item) }) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Hapus Data",
                        tint = Color(0xFFD32F2F)
                    )
                }
            }
        }
    }
}


