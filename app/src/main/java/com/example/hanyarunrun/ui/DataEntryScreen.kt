package com.example.hanyarunrun.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hanyarunrun.viewmodel.DataViewModel

@Composable
fun DataEntryScreen(navController: NavHostController, viewModel: DataViewModel) {
    val context = LocalContext.current
    var kodeProvinsi by remember { mutableStateOf("") }
    var namaProvinsi by remember { mutableStateOf("") }
    var kodeKabupatenKota by remember { mutableStateOf("") }
    var namaKabupatenKota by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }
    var satuan by remember { mutableStateOf("") }
    var tahun by remember { mutableStateOf("") }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Menyesuaikan padding agar tidak tertutup navbar
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Input Data",
                    style = MaterialTheme.typography.headlineMedium
                )
                OutlinedTextField(
                    value = kodeProvinsi,
                    onValueChange = { kodeProvinsi = it },
                    label = { Text("Kode Provinsi", fontSize = 12.sp) },
                    textStyle = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = namaProvinsi,
                    onValueChange = { namaProvinsi = it },
                    label = { Text("Nama Provinsi", fontSize = 12.sp) },
                    textStyle = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = kodeKabupatenKota,
                    onValueChange = { kodeKabupatenKota = it },
                    label = { Text("Kode Kabupaten/Kota", fontSize = 12.sp) },
                    textStyle = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = namaKabupatenKota,
                    onValueChange = { namaKabupatenKota = it },
                    label = { Text("Nama Kabupaten/Kota", fontSize = 12.sp) },
                    textStyle = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = total,
                    onValueChange = { total = it },
                    label = { Text("Total", fontSize = 12.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = satuan,
                    onValueChange = { satuan = it },
                    label = { Text("Satuan", fontSize = 12.sp) },
                    textStyle = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = tahun,
                    onValueChange = { tahun = it },
                    label = { Text("Tahun", fontSize = 12.sp) },
                    textStyle = TextStyle(fontSize = 14.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        // Memanggil fungsi insertData pada ViewModel
                        viewModel.insertData(
                            kodeProvinsi = kodeProvinsi,
                            namaProvinsi = namaProvinsi,
                            kodeKabupatenKota = kodeKabupatenKota,
                            namaKabupatenKota = namaKabupatenKota,
                            total = total,
                            satuan = satuan,
                            tahun = tahun
                        )
                        Toast.makeText(context, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                        // Navigasi ke tampilan daftar data
                        navController.navigate("list")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit Data")
                }
            }
        }
    }

