package com.example.datakompgaming.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment


@Composable
fun printTopBarIcon(navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    var showMenu = false
    Row(horizontalArrangement = Arrangement.End,modifier = Modifier
        .background(Color(0xFF6200FF))
        .fillMaxWidth()) {

        Box{
            IconButton(onClick = { expanded = true }) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Person",
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier.size(35.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
                    Text("handlekurv")
                }
                DropdownMenuItem(onClick = { /* Handle settings! */ }) {
                    Text("bestillinger")
                }
                DropdownMenuItem(onClick = { /* Handle send feedback! */ }) {
                    Text("logg ut")
                }
            }
        }
    }
}


