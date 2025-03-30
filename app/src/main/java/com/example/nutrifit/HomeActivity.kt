package com.example.nutrifit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrifit.ui.theme.NutrifitTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutrifitTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFFFFDD0), Color(0xFFFFE4E1))
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
            ) {
                // Profile Menu Top Left
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopStart
                ) {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Profile",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Edit Profile") },
                            onClick = {
                                showMenu = false
                                Toast.makeText(context, "Edit Profile", Toast.LENGTH_SHORT).show()
                                // TODO: Navigate to EditProfileActivity
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Privacy Policy") },
                            onClick = {
                                showMenu = false
                                Toast.makeText(context, "Privacy Policy", Toast.LENGTH_SHORT).show()
                                // TODO: Navigate to PrivacyPolicyActivity
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Welcome to NutriFit!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Scrollable Column for cards
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FeatureCard(
                        title = "Meals",
                        imageRes = R.drawable.meal,
                        onClick = {
                            Toast.makeText(context, "Navigate to Meal Breakdown", Toast.LENGTH_SHORT).show()
                            // TODO: Navigate to MealBreakdownActivity
                        }
                    )

                    FeatureCard(
                        title = "Workout",
                        imageRes = R.drawable.workout,
                        onClick = {
                            Toast.makeText(context, "Navigate to Workout Planner", Toast.LENGTH_SHORT).show()
                            // TODO: Navigate to WorkoutPlannerActivity
                        }
                    )

                    FeatureCard(
                        title = "Nutrition Tips",
                        imageRes = R.drawable.nutrition, // ← Add this image
                        onClick = {
                            Toast.makeText(context, "Navigate to Nutrition Tips", Toast.LENGTH_SHORT).show()
                            // TODO: Navigate to NutritionTipsActivity
                        }
                    )

                    FeatureCard(
                        title = "Progress Tracker",
                        imageRes = R.drawable.progress, // ← Add this image
                        onClick = {
                            Toast.makeText(context, "Navigate to Progress Tracker", Toast.LENGTH_SHORT).show()
                            // TODO: Navigate to ProgressTrackerActivity
                        }
                    )
                }
            }
        }
    }
}



@Composable
fun FeatureCard(
    title: String,
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        shape = MaterialTheme.shapes.extraLarge,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "$title Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp)) // 🎉 Rounded image!
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomePreview() {
    NutrifitTheme {
        HomeScreen()
    }
}
