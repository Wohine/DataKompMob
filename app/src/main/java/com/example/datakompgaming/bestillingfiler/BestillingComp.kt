package com.example.datakompgaming.bestillingfiler

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.datakompgaming.screen.printBotBarIcon
import com.example.datakompgaming.screen.printTopBarIcon


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun printOrders(bestillingListe: List<Order>, navController: NavController) {
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState(), enabled = true)
                .background(Color.White),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Dine Bestillinger")
            for(bestilling in bestillingListe){
                BestillingerCard(bestilling)
                Spacer(modifier = Modifier.height(5.dp))
            }
            Spacer(modifier = Modifier.height(45.dp))
        }
    }
}
@Composable
fun BestillingerCard(bestilling: Order) {
    Row(modifier = Modifier
        .border(3.dp, Color.Black)
        .padding(10.dp)
    ) {
        Column() {
            Text(text = "Bestilling")
            Text(text = bestilling.dato)
            Text(text = bestilling.key)
            for (item in bestilling.liste){
                printProdukt(item)
            }
            Text(text = bestilling.totalPris)
        }
    }
}



@Composable
fun printProdukt(produkt: Produkt){
    Row() {
        itemBilde(produkt = produkt)
        Column(){
            Text(text = produkt.description)
            Text(text = produkt.pris)
            Text(text = produkt.stjerner)
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun itemBilde(produkt: Produkt) {
    Image(
        painter = painterResource(id = produkt.bildeId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
    )
}