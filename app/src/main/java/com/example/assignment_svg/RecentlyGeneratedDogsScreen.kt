package com.example.assignment_svg

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentlyGeneratedDogsScreen(
    navController: NavController,
    dogImages: List<String>, // Receive dog images as parameter
    onClearImages: () -> Unit
) {
    // Initialize CacheHelper to load images and clear images
    val cacheHelper = CacheHelper(LocalContext.current)
    val buttonColor = Color(66f / 255f, 134f / 255f, 244f / 255f)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recently Generated Dogs") },
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Recently Generated Dog Images", modifier = Modifier.padding(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {
                dogImages.forEach { image ->
                    Image(
                        painter = rememberAsyncImagePainter(image),
                        contentDescription = "Dog Image",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(8.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Clear the cache and update the state to trigger recomposition
                    cacheHelper.clearCache()
                    onClearImages()  // Clear images from the screen
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text("Clear Images")
            }
        }
    }
}
