package com.example.datakompgaming.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun test(navController: NavController) {
    Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.padding(all = 2.dp)) {
            Text(text = "DataKomp")
        }
        Row(modifier = Modifier.padding(all = 2.dp)) {
            Button(
                onClick = {
                    Produkter()
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
            ) {
                Text(text = "Logg inn eller registrer ny bruker")
            }
        }
    }
}