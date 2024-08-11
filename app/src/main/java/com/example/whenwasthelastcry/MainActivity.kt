package com.example.whenwasthelastcry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.whenwasthelastcry.ui.theme.WhenWasTheLastCryTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ScreenModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ScreenModel()
        setContent {
            WhenWasTheLastCryTheme{
                       Screen(      this.viewModel)
            }
        }
    }
}

