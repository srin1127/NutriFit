package com.example.nutrifit

import android.content.Intent
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
import androidx.compose.ui.layout.ContentScale
import com.example.nutrifit.screens.EditProfileActivity
import com.example.nutrifit.screens.MealBreakdownActivity
import com.example.nutrifit.screens.NutritionTipsActivity
import com.example.nutrifit.screens.PrivacyPolicyActivity
import com.example.nutrifit.screens.ProgressTrackerActivity
import com.example.nutrifit.screens.WorkoutPlannerActivity

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


            // Main Content (under the top padding)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 64.dp), // Padding to avoid overlapping the top bar
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Welcome to NutriFit!",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Box {
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
                                    context.startActivity(Intent(context, EditProfileActivity::class.java))
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Privacy Policy") },
                                onClick = {
                                    showMenu = false
                                    context.startActivity(Intent(context, PrivacyPolicyActivity::class.java))
                                }
                            )
                        }
                    }
                }



                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FeatureCard(
                        title = "Meals Breakdown",
                        imageRes = R.drawable.meal,
                        onClick = {
                            context.startActivity(Intent(context, MealBreakdownActivity::class.java))
                        }
                    )

                    FeatureCard(
                        title = "Workout Planner",
                        imageRes = R.drawable.workout,
                        onClick = {
                            context.startActivity(Intent(context, WorkoutPlannerActivity::class.java))
                        }
                    )

                    FeatureCard(
                        title = "Nutrition Tips",
                        imageRes = R.drawable.nutrition,
                        onClick = {
                            context.startActivity(Intent(context, NutritionTipsActivity::class.java))
                        }
                    )

                    FeatureCard(
                        title = "Progress Tracker",
                        imageRes = R.drawable.progress,
                        onClick = {
                            context.startActivity(Intent(context, ProgressTrackerActivity::class.java))
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
            .height(180.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "$title Image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop // Fills the entire card
            )

            // Text Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                        )
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
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
