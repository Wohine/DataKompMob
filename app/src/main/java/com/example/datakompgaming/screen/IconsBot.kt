package com.example.datakompgaming.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.datakompgaming.bestillingfiler.bestillingHent
import com.example.datakompgaming.ui.theme.DataKompGamingTheme

/**
 * lager selve botbar
 */
@Composable
fun printBotBarIcon(navController: NavController, nummer: Int){
    // 0 = hjem, 1 = chat, 2 = brukt produkt skjema, 3 = om oss, 4 = produkter
    DataKompGamingTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 17.dp)
            ) {

                IconButton(modifier = Modifier.padding(horizontal = 10.dp)
                        then (Modifier.size(45.dp)),
                    onClick = {
                        navController.navigate("HomePage")
                    }) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "hjemside",
                        Modifier.size(35.dp),
                        tint = if (nummer == 0) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary
                    )
                }
                IconButton(modifier = Modifier.padding(horizontal = 10.dp)
                        then (Modifier.size(45.dp)),
                    onClick = {
                        navController.navigate("chat")
                    }) {
                    Icon(
                        Icons.Default.Chat,
                        contentDescription = "chat",
                        Modifier.size(35.dp),
                        tint = if (nummer == 1) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary
                    )
                }
                IconButton(modifier = Modifier.padding(horizontal = 10.dp)
                        then (Modifier.size(45.dp)),
                    onClick = {
                        navController.navigate("bruktProduktSkjema")
                    }) {
                    Icon(
                        Icons.Default.Publish,
                        contentDescription = "bruktProduktSkjema",
                        Modifier.size(35.dp),
                        tint = if (nummer == 2) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary
                    )
                }
                IconButton(modifier = Modifier.padding(horizontal = 10.dp)
                        then (Modifier.size(45.dp)),
                    onClick = {
                        navController.navigate("BrukteProdukter")
                    }) {
                    Icon(
                        Icons.Default.Museum,
                        contentDescription = "brukte produkter",
                        Modifier.size(35.dp),
                        tint = if (nummer == 3) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary
                    )
                }
                IconButton(modifier = Modifier.padding(horizontal = 10.dp)
                        then (Modifier.size(45.dp)),
                    onClick = {
                        navController.navigate("Produkter")
                    }) {
                    Icon(
                        Icons.Default.Shop,
                        contentDescription = "produkter",
                        Modifier.size(35.dp),
                        tint = if (nummer == 4) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}