package com.example.whenwasthelastcry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.whenwasthelastcry.ui.theme.WhenWasTheLastCryTheme

@Composable
fun Screen( screenViewModel: ScreenModel = viewModel()) {
    val lastCryLabel by screenViewModel.lastCryText.observeAsState("")
    val previousCryAttempts = screenViewModel.prevousAttempts.observeAsState()
    val showConfirmation = screenViewModel.confirmationState.observeAsState()
    val cryBaby = screenViewModel.cryBaby.observeAsState("")


    WhenWasTheLastCryTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                if (previousCryAttempts.value!!.size>0) {
                    Text(
                        text = "Korrábi hiszik:", textAlign = TextAlign.Center
                    )


                    Spacer(modifier = Modifier.height(20.dp))
                }
                LazyColumn {
                    items(previousCryAttempts.value ?: emptyList()) {

                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = it, textAlign = TextAlign.Center)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(text = lastCryLabel, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(20.dp))
                if (showConfirmation.value == true) {
                    AlertDialog(onDismissRequest = { screenViewModel.hideConfirm() },
                        title = { Text("Ki volt az és miért?") },
                        text = {
                            Column {
                                Text("Nos: ")
                                Spacer(modifier = Modifier.height(8.dp))
                                TextField(value = cryBaby.value, onValueChange = {screenViewModel.setCryBaby(it)})

                            }
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                screenViewModel.setLastCry()
                                screenViewModel.hideConfirm()
                            }) {
                                Text("Rögzít")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { screenViewModel.hideConfirm()  }) {
                                Text("Mégsem")
                            }
                        }
                    )

                } else {
                    Button(
                        onClick = { screenViewModel.showConfirm() },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier
                            .size(200.dp)
                    ) {
                        Text(
                            text = "NYAFF !!!",
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
}

