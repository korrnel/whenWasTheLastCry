package com.example.whenwasthelastcry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whenwasthelastcry.ui.theme.WhenWasTheLastCryTheme
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Screen( screenViewModel: ScreenModel = viewModel()) {
    val lastCryLabel by screenViewModel.lastCryText.observeAsState("")

    WhenWasTheLastCryTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) { Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)) {
            Text(text = lastCryLabel, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { screenViewModel.setLastCry() },
                shape = CircleShape,
                colors= ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .size(200.dp)
            ) {
               Text(
                    text = "HISZTI !!!",
                    color = Color.White,
                    modifier = Modifier.background(Color.Red),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            }
        }
    }

}

