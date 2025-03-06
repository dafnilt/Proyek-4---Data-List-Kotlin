package com.example.hanyarunrun.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    Scaffold(
        bottomBar = {
            AnimatedBottomBar(navController, currentRoute)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(
                text = "List Data",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

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

@Composable
fun AnimatedBottomBar(navController: NavHostController, currentRoute: String) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        val items = listOf(
            BottomNavItem("Home", Icons.Default.Home, "list"),
            BottomNavItem("Tambah Data", Icons.Default.Add, "form"),
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
                            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                label = {
                    AnimatedVisibility(visible = isSelected) {
                        Text(item.label)
                    }
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
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
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface).padding(16.dp)
        ) {
            Text(
                text = "Provinsi: ${item.namaProvinsi} (${item.kodeProvinsi})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Kabupaten/Kota: ${item.namaKabupatenKota} (${item.kodeKabupatenKota})",
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
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.navigate("edit/${item.id}")
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Edit")
                }
                JetsnackTheme {
                    JetsnackButton(
                        onClick = {
                            viewModel.deleteData(item)
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}


