package com.example.assignment_svg

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // Define the button color using RGB values
    val buttonColor = Color(66f / 255f, 134f / 255f, 244f / 255f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Dog Photo App")

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate("generate") },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor) // Correct color application
        ) {
            Text("Generate Dog Image")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { navController.navigate("recent") },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor) // Correct color application
        ) {
            Text("View Recently Generated Dogs")
        }
    }
}
