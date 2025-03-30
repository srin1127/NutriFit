package com.example.nutrifit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nutrifit.screens.*
import com.example.nutrifit.ui.theme.NutrifitTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Snackbar

import kotlinx.coroutines.launch


class HomeActivity : ComponentActivity() {

    private val logoutDelayMillis: Long = 60_000 // 1 minute
    private val warningBeforeLogoutMillis: Long = 10_000 // 10 seconds
    private val handler = Handler(Looper.getMainLooper())
    private var warningShown = false

    private val logoutRunnable = Runnable {
        FirebaseAuth.getInstance().signOut()
        clearLoginFlag()
        Toast.makeText(this, "Logged out due to inactivity", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private val warningRunnable = Runnable {
        showCountdownSnackbar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!wasLoggedIn()) {
            // prevent unauthorized access
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        resetInactivityTimer()

        setContent {
            NutrifitTheme {
                HomeScreen(onLogoutWarning = {
                    showCountdownSnackbar()
                })
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        resetInactivityTimer()
        return super.dispatchTouchEvent(ev)
    }

    private fun resetInactivityTimer() {
        handler.removeCallbacks(logoutRunnable)
        handler.removeCallbacks(warningRunnable)
        handler.postDelayed(warningRunnable, logoutDelayMillis - warningBeforeLogoutMillis)
        handler.postDelayed(logoutRunnable, logoutDelayMillis)
        warningShown = false
    }

    private fun showCountdownSnackbar() {
        // We'll trigger Composable Snackbar via state
        warningShown = true
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(logoutRunnable)
        handler.removeCallbacks(warningRunnable)
    }

    // SharedPreferences - Login Flag
    private fun wasLoggedIn(): Boolean {
        val prefs = getSharedPreferences("nutrifit_prefs", Context.MODE_PRIVATE)
        return prefs.getBoolean("is_logged_in", false)
    }

    private fun clearLoginFlag() {
        val prefs = getSharedPreferences("nutrifit_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("is_logged_in", false).apply()
    }
}


@Composable
fun HomeScreen(onLogoutWarning: () -> Unit) {
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Show logout warning snackbar
    LaunchedEffect(Unit) {
        delay(50_000) // This is just for demo/testing
        showSnackbar = true
    }

    if (showSnackbar) {
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar("You will be logged out in 10 seconds due to inactivity.")
            showSnackbar = false
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {

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
                        .padding(horizontal = 24.dp, vertical = 64.dp),
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
        HomeScreen(onLogoutWarning = {})
    }
}

