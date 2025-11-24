package com.example.soporte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoporteAppRoot()
        }
    }
}

@Composable
private fun SoporteAppRoot() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Text(text = "Hola soporte")
    }
}

@Preview(showBackground = true)
@Composable
private fun SoporteAppPreview() {
    SoporteAppRoot()
}
