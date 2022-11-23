package com.example.datakompgaming.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.datakompgaming.bestillingfiler.BestillingerCard
import com.example.datakompgaming.bestillingfiler.Order
import com.example.datakompgaming.bestillingfiler.printProdukt
import com.example.datakompgaming.handlekurv.HandlekurvObject
import com.example.datakompgaming.handlekurv.produktOppdateringDB
import com.example.datakompgaming.produkt.ProduktObject
import com.example.datakompgaming.produkt.ProdukterFire


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun printHandlekurv(navController: NavController) {
    Scaffold(
        bottomBar = {
            printBotBarIcon(navController = navController, 5)
        },
        topBar = {
            printTopBarIcon(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState(), enabled = true)
                .background(Color.White),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Din handlekurv")
            for(item in HandlekurvObject.handlekurvListe){
                HandlekurvCard(item, navController)
                Spacer(modifier = Modifier.height(5.dp))
            }
            Button(onClick = {
                updateVarerPaLager()
                HandlekurvObject.handlekurvListe.clear()
                navController.navigate("Handlekurv")
            }) {
                Text(text = "kjøp varer!")
            }
            Spacer(modifier = Modifier.height(65.dp))
        }
    }
}

@Composable
fun HandlekurvCard(item: ProdukterFire,navController: NavController) {

    var pris = item.pris.toString()

    Row(modifier = Modifier
        .border(3.dp, Color.Black)
        .padding(10.dp)
        .fillMaxWidth()
    ) {
        AsyncImage(
        model = item.bilde,
        contentDescription = "null",
        modifier = Modifier
            .fillMaxSize(0.5F)
        )
        Column() {
            Text(text = item.tittel)
            Text(text = item.rating + " stjerners rangering")
            Text(text = item.varebeholdning+ " på lager")
            Text(text = pris + "kr")
            Button(onClick = {
                HandlekurvObject.handlekurvListe.remove(item)
                navController.navigate("Handlekurv")
            }) {
                Text(text = "fjern fra handlekurv",
                    textAlign = TextAlign.Center)
            }
        }
    }
}

fun updateVarerPaLager(){
    for (item in HandlekurvObject.handlekurvListe)
        produktOppdateringDB(item)
}