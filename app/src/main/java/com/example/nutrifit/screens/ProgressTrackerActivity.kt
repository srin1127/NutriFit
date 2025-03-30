package com.example.nutrifit.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutrifit.ui.theme.NutrifitTheme

class ProgressTrackerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutrifitTheme {
                ProgressTrackerScreen()
            }
        }
    }
}

@Composable
fun ProgressTrackerScreen() {
    val progressList = listOf(
        ProgressItem("Workouts Completed", "This week", 0.8f), // 80%
        ProgressItem("Meals Logged", "This week", 0.65f),     // 65%
        ProgressItem("Water Intake", "Today", 0.5f),          // 50%
        ProgressItem("Sleep Goal", "Avg this week", 0.9f)     // 90%
    )

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
                    .padding(16.dp)
            ) {
                Text(
                    text = "Progress Tracker",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(progressList) { item ->
                        ProgressCard(item)
                    }
                }
            }
        }
    }
}

data class ProgressItem(
    val title: String,
    val subtitle: String,
    val progress: Float // 0f to 1f
)

@Composable
fun ProgressCard(item: ProgressItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = item.progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = Color.LightGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressTrackerPreview() {
    NutrifitTheme {
        ProgressTrackerScreen()
    }
}
