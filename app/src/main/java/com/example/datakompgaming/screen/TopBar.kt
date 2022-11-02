package com.example.datakompgaming.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.example.datakompgaming.mainActivity
import com.example.datakompgaming.ui.theme.DataKompGamingTheme


@Composable
fun printTopBarIcon(navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    var showMenu = false
    DataKompGamingTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        ) {
            Row(
                horizontalArrangement = Arrangement.End, modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Person",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("handlekurv") },
                            onClick = { /* Handle edit! */ })
                        DropdownMenuItem(
                            text = { Text("bestillinger") },
                            onClick = {
                                navController.navigate("bestilling")
                            })
                        DropdownMenuItem(
                            text = { Text("logg ut") },
                            onClick = { mainActivity?.logOut() })
                    }
                }
            }
        }
    }
}