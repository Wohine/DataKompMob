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
import coil.compose.AsyncImage
import com.example.datakompgaming.screen.printBotBarIcon
import com.example.datakompgaming.screen.printTopBarIcon

/**
 * funksjon som printer ut bestillingene til brukeren
 * @param bestillingListe
 * @param navController
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun printOrders(bestillingListe: List<Order>, navController: NavController) {
    if (bestillingListe.isEmpty()) // henter bestillinger om det ikke har blitt gjort
        bestillingHent()

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
            for(bestilling in bestillingListe){ // printer hver bestilling
                BestillingerCard(bestilling)
                Spacer(modifier = Modifier.height(5.dp))
            }
            Spacer(modifier = Modifier.height(45.dp))
        }
    }
}

/**
 * funksjon som lager et card av hver bestilling
 * @param bestilling
 */
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
            for (item in bestilling.liste){ // løkke av hver produkt i en bestilling
                printProdukt(item)
            }
            Text(text = bestilling.totalPris)
        }
    }
}


/**
 * funksjon som skriver ut hvert produkt i en bestilling
 * @param produkt
 */
@Composable
fun printProdukt(produkt: Produkt){
    Row(modifier = Modifier.border(1.dp, Color.Black)) {
        itemBilde(produkt = produkt)
        Column(){
            Text(text = produkt.tittel)
            Text(text = produkt.pris)
        }
    }
}

/**
 * funksjon som printer et bilde til hvert produkt
 * @param produkt
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun itemBilde(produkt: Produkt) {
    AsyncImage(
        model = produkt.bilde,
        contentDescription = "null",
        modifier = Modifier
            .fillMaxWidth(0.3f),
        alignment = Alignment.CenterStart
    )
}
