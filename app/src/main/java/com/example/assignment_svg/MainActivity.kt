package com.example.assignment_svg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Using the default MaterialTheme
            MaterialTheme {
                // State to store dog images
                var dogImages by remember { mutableStateOf<List<String>>(listOf()) }
                val navController = rememberNavController()

                // Scaffold to provide the layout
                Scaffold { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable("home") {
                            HomeScreen(navController)
                        }
                        composable("generate") {
                            GenerateDogsScreen(
                                navController,
                                dogApiService = DogApiService.create(),
                                onImagesGenerated = { image ->
                                    dogImages = dogImages + image
                                }
                            )
                        }
                        composable("recent") {
                            RecentlyGeneratedDogsScreen(
                                navController = navController,
                                dogImages = dogImages, // Pass the dog images to the screen
                                onClearImages = {
                                    dogImages =
                                        emptyList() // Clear the images when the button is clicked
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
