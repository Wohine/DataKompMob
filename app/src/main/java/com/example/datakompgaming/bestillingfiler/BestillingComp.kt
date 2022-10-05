package com.example.datakompgaming.bestillingfiler

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun printOrders(bestillingListe: List<Order>) {
    Column() {
        Text(text = "Dine bestillinger")
        for (bestilling in bestillingListe){
            Column(modifier = Modifier
                .padding(10.dp)
                .border(BorderStroke(1.dp, SolidColor(Color.Black)))) {
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