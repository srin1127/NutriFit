package com.example.nutrifit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFFFFDD0),
                            Color(0xFFFFE4E1)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Top Row: Profile Icon (Dropdown menu)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopStart
                ) {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
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
                                // TODO: Show PrivacyPolicyActivity or Dialog
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Welcome to NutriFit!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
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
                }
            }
        }
    }
}


@Composable
fun FeatureCard(
    title: String,
    imageRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .width(160.dp)
        .height(180.dp)
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.extraLarge,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "$title Icon",
                modifier = Modifier
                    .size(80.dp)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
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
