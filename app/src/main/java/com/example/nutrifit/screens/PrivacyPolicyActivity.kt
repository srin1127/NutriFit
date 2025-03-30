package com.example.nutrifit.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrifit.ui.theme.NutrifitTheme

class PrivacyPolicyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutrifitTheme {
                PrivacyPolicyScreen()
            }
        }
    }
}

@Composable
fun PrivacyPolicyScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFFFFDD0), Color(0xFFFFE4E1))
                    )
                )
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Privacy Policy",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Last updated: March 30, 2025",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                TextSection(
                    title = "Data Collection",
                    body = "NutriFit collects user-provided data such as name, email, workouts, and meal logs to personalize your experience. We may also collect anonymized usage statistics to improve app functionality."
                )

                TextSection(
                    title = "How We Use Your Data",
                    body = "Your data is used to provide personalized insights and support fitness tracking. We do not sell or share personal data with third-party advertisers."
                )

                TextSection(
                    title = "Storage & Security",
                    body = "Your data is securely stored in Firebase with encryption and authentication measures. We ensure only authorized users have access to their own data."
                )

                TextSection(
                    title = "Your Rights",
                    body = "You have the right to access, update, or delete your data at any time. Contact our support team if you'd like to request any changes."
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "By using NutriFit, you agree to our privacy policy.",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun TextSection(title: String, body: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = body,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyPreview() {
    NutrifitTheme {
        PrivacyPolicyScreen()
    }
}
