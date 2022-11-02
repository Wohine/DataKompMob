package com.example.datakompgaming.screen

import android.util.Log
import androidx.compose.animation.core.snap
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.datakompgaming.produkt.ProduktObject.produktListe
import com.google.firebase.database.*
import com.google.protobuf.Value

@Composable
fun Test(navController: NavController) {

    Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.padding(all = 2.dp)) {
            Text(text = "Testside :)")
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
                Text(text = "Kjør test")
            }
        }
    }
}