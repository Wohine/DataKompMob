package com.example.datakompgaming.screen
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.handlekurv.HandlekurvObject.BruktHandleliste
import com.example.datakompgaming.handlekurv.HandlekurvObject.handlekurvListe
import com.example.datakompgaming.mainActivity
import com.example.datakompgaming.ui.theme.DataKompGamingTheme


@Composable
fun printTopBarIcon(navController: NavController){
    var expanded by remember { mutableStateOf(false) }

    /**
     * viser hvor mange ting som ligger i handlekurven
     */
    var totalBasketCount = (handlekurvListe.size + BruktHandleliste.size).toString()
    DataKompGamingTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Row(
                horizontalArrangement = Arrangement.End, modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box {
                    /**
                     * logikk for å bli trykket på
                     */
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Person",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    /**
                     * logikk for å bli trykket på
                     */
                    IconButton(modifier = Modifier.absoluteOffset(-300.dp,0.dp), onClick = { navController.navigate("Handlekurv") }) {
                        Icon(
                            Icons.Default.ShoppingBasket,
                            contentDescription = "basket",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(35.dp)
                        )
                        Text( text = totalBasketCount, modifier= Modifier, fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,color = Color(0xFF0888c4) )
                    }
                    /**
                     * dropdown meny elementer
                     */
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Din side") },
                            onClick = {
                                navController.navigate("UserPage")
                            })
                        DropdownMenuItem(
                            text = { Text("Handlekurv") },
                            onClick = {
                                navController.navigate("Handlekurv")
                            })
                        DropdownMenuItem(
                            text = { Text("Bestillinger") },
                            onClick = {
                                navController.navigate("bestilling")
                            })
                        DropdownMenuItem(
                            text = { Text("Om Oss") },
                            onClick = {
                                navController.navigate("OmOss")
                            })
                        DropdownMenuItem(
                            text = { Text("Kundeservice") },
                            onClick = { navController.navigate("kundeservice") })
                        DropdownMenuItem(
                            text = { Text("Logg ut") },
                            onClick = { mainActivity?.logOut() })
                    }
                }
            }
        }
    }
}