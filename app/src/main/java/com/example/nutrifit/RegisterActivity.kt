package com.example.nutrifit

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.nutrifit.ui.theme.NutrifitTheme
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutrifitTheme {
                RegisterScreen()
            }
        }
    }
}

@Composable
fun RegisterScreen() {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFFDD0), // Cream
                            Color(0xFFFFE4E1)  // Blush
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = false
                    },
                    label = { Text("Email") },
                    isError = emailError,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
                if (emailError) {
                    Text(
                        text = "Invalid email format",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = false
                    },
                    label = { Text("Password") },
                    isError = passwordError,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (passwordError) {
                    Text(
                        text = "Password must be at least 6 characters",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        confirmPasswordError = false
                    },
                    label = { Text("Confirm Password") },
                    isError = confirmPasswordError,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (confirmPasswordError) {
                    Text(
                        text = "Passwords do not match",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                //val context = LocalContext.current
                val auth = FirebaseAuth.getInstance()

                Button(
                    onClick = {
                        emailError = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        passwordError = password.length < 6
                        confirmPasswordError = password != confirmPassword

                        if (!emailError && !passwordError && !confirmPasswordError) {
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                                        context.startActivity(Intent(context, LoginActivity::class.java))

                                        // Optional: close RegisterActivity to prevent going back
                                        // (context as? Activity)?.finish()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Registration failed: ${task.exception?.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register")
                }



                Spacer(modifier = Modifier.height(12.dp))

                val context = LocalContext.current

                TextButton(onClick = {
                    context.startActivity(Intent(context, LoginActivity::class.java))
                }) {
                    Text("Already have an account? Login")
                }
            }
        }
    }
}
