package com.example.assignment_svg

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateDogsScreen(
    navController: NavController,
    dogApiService: DogApiService,
    onImagesGenerated: (String) -> Unit
) {

    var imageUrl by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val buttonColor = Color(66f / 255f, 134f / 255f, 244f / 255f)

    // Initialize CacheHelper to save/load dog images
    val cacheHelper = CacheHelper(LocalContext.current)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Generate Dog") },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { navController.popBackStack() }) {
                        androidx.compose.material3.Icon(
                            painter = androidx.compose.ui.res.painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            imageUrl?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Dog Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            val response = dogApiService.getRandomDogImage()
                            imageUrl = response.message

                            // Add new image to the cache
                            var dogImages = cacheHelper.loadImages().toMutableList()

                            // If there are more than 20 images, remove the oldest one
                            if (dogImages.size >= 20) {
                                dogImages.removeAt(0)
                            }

                            dogImages.add(response.message)
                            cacheHelper.saveImages(dogImages)

                            onImagesGenerated(response.message)  // Notify MainActivity
                        } catch (e: Exception) {
                            imageUrl = null // Handle error
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)

            ) {
                Text("Generate Dog")
            }
        }
    }
}

