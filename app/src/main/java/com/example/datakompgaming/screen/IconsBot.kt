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
import com.example.datakompgaming.ui.theme.DataKompGamingTheme


@Composable
fun printBotBarIcon(navController: NavController, nummer: Int){
    // 0 = hjem, 1 = chat, 2 = kundesevice, 3 = om oss, 4 = produkter
    DataKompGamingTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 17.dp)
            ) {

                IconButton(modifier = Modifier.padding(horizontal = 15.dp)
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
                IconButton(modifier = Modifier.padding(horizontal = 15.dp)
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
                IconButton(modifier = Modifier.padding(horizontal = 15.dp)
                        then (Modifier.size(45.dp)),
                    onClick = {
                        navController.navigate("bruktProduktSkjema")
                    }) {
                    Icon(
                        Icons.Default.Help,
                        contentDescription = "kundeservice",
                        Modifier.size(35.dp),
                        tint = if (nummer == 2) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary
                    )
                }
                IconButton(modifier = Modifier.padding(horizontal = 15.dp)
                        then (Modifier.size(45.dp)),
                    onClick = {
                        navController.navigate("omOss")
                    }) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = "om oss ",
                        Modifier.size(35.dp),
                        tint = if (nummer == 3) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary
                    )
                }
                IconButton(modifier = Modifier.padding(horizontal = 15.dp)
                        then (Modifier.size(45.dp)),
                    onClick = {
                        navController.navigate("produkter")
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