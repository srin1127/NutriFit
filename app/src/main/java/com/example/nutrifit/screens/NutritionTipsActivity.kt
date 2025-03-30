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

class NutritionTipsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutrifitTheme {
                NutritionTipsScreen()
            }
        }
    }
}

@Composable
fun NutritionTipsScreen() {
    val tips = listOf(
        NutritionTip("Stay Hydrated", "Drink at least 2–3 liters of water daily to keep your body functioning properly."),
        NutritionTip("Balanced Meals", "Ensure every meal contains protein, healthy fats, and complex carbs."),
        NutritionTip("Limit Sugar", "Reduce added sugars in your diet to avoid energy crashes and weight gain."),
        NutritionTip("Eat More Fiber", "Include fruits, vegetables, and whole grains to improve digestion."),
        NutritionTip("Mindful Eating", "Eat slowly and without distractions to better control portions.")
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
                    .padding(
                        WindowInsets.systemBars.asPaddingValues()
                    )
            ) {
                Text(
                    text = "Nutrition Tips",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(tips) { tip ->
                        NutritionCard(tip)
                    }
                }
            }
        }
    }
}


data class NutritionTip(
    val title: String,
    val description: String
)

@Composable
fun NutritionCard(tip: NutritionTip) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = tip.title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tip.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NutritionTipsPreview() {
    NutrifitTheme {
        NutritionTipsScreen()
    }
}
