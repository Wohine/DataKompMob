package com.example.datakompgaming.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.datakompgaming.ui.theme.DataKompGamingTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test2Chris(navController: NavController) {
    DataKompGamingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.onBackground
        ) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )

                    }
                },

                bottomBar = {
                    printBotBarIcon(navController = navController, 5)
                }

            )
            { values ->
                LazyColumn(contentPadding = values) {
                    items(6) {
                        ImageCard(
                            title = "Yada yada",
                            description = "Yada yada yada yada yada yada yada yada yada yada yada ",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }

}