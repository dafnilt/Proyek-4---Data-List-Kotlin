package com.example.hanyarunrun.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hanyarunrun.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel,
    modifier: Modifier = Modifier
) {
    val user by viewModel.user.observeAsState()
    val context = LocalContext.current

    var isEditing by remember { mutableStateOf(false) }
    var tempName by remember { mutableStateOf(user?.nama ?: "") }
    var tempId by remember { mutableStateOf(user?.nim ?: "") }
    var tempEmail by remember { mutableStateOf(user?.email ?: "") }
    var tempProfileImg by remember { mutableStateOf<Bitmap?>(byteArrayToBitmap(user?.profileImg)) }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                tempProfileImg = bitmap
                showToast(context, "Foto berhasil diunggah")
            }
        }

    LaunchedEffect(user) {
        user?.let {
            tempName = it.nama
            tempId = it.nim
            tempEmail = it.email
            tempProfileImg = byteArrayToBitmap(it.profileImg)
        }
    }


    Scaffold(
        bottomBar = { AnimatedBottomBar(navController, currentRoute = "profile") } // Tambahkan bottom bar di dalam Scaffold
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Brush.verticalGradient(listOf(Color.White, Color(0xFFB4EBE6))))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Profil",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            // Box Foto Profil
            Box(
                modifier = Modifier
                    .size(130.dp) // Lebih besar
                    .border(
                        4.dp,
                        MaterialTheme.colorScheme.primary,
                        CircleShape
                    ) // Border lebih tebal
                    .background(Color.LightGray, CircleShape)
                    .shadow(8.dp, CircleShape), // Tambahkan shadow
                contentAlignment = Alignment.Center
            ) {
                tempProfileImg?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(130.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } ?: Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Default Profile Picture",
                    tint = Color.Gray,
                    modifier = Modifier.size(90.dp) // Ukuran lebih besar
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isEditing) {
                OutlinedTextField(
                    value = tempName,
                    onValueChange = { tempName = it },
                    label = { Text("Nama") },
                    modifier = Modifier.fillMaxWidth(0.85f) // Lebih kecil dari full width
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = tempId,
                    onValueChange = { tempId = it },
                    label = { Text("NIM") },
                    modifier = Modifier.fillMaxWidth(0.85f)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = tempEmail,
                    onValueChange = { tempEmail = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(0.85f)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { imagePickerLauncher.launch("image/*") },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Upload Foto", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        tempProfileImg?.let {
                            viewModel.updateUser(tempName, tempId, tempEmail, it)
                            isEditing = false
                            showToast(context, "Profil berhasil diperbarui")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    enabled = tempProfileImg != null
                ) {
                    Text("Simpan", fontWeight = FontWeight.Bold)
                }
            } else {
                Text(
                    text = user?.nama ?: "Belum edit Profil",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${user?.nim ?: "-"}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = user?.email ?: "-",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { isEditing = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Edit Profil", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

fun byteArrayToBitmap(byteArray: ByteArray?): Bitmap? {
    return byteArray?.let {
        BitmapFactory.decodeByteArray(it, 0, it.size)
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}



