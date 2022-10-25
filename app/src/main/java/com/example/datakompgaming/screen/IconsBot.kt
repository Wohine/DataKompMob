package com.example.datakompgaming.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.R
import com.example.datakompgaming.bestillingfiler.Produkt
import com.example.datakompgaming.ui.theme.Purple700

@Composable
fun printBotBarIcon(navController: NavController){

    Row(modifier = Modifier
        .background(Color(0xFF6200FF))
        .fillMaxWidth().padding(5.dp)) {

        IconButton(modifier = Modifier.padding(horizontal = 20.dp)
        then(Modifier.size(40.dp)),
            onClick = {
                navController.navigate("HomePage")
            }) {
            Icon(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Home",
                tint = Color.White
            )
        }
        IconButton(modifier = Modifier.padding(horizontal = 20.dp)
        then(Modifier.size(40.dp)),
            onClick = {
                navController.navigate("chat")
            }) {
            Icon(
                painter = painterResource(id = R.drawable.chat),
                contentDescription = "chat",
                tint = Color.White
            )
        }
        IconButton(modifier = Modifier.padding(horizontal = 20.dp)
        then(Modifier.size(40.dp)),
            onClick = {
                navController.navigate("kundeservice")
            }) {
            Icon(
                painter = painterResource(id = R.drawable.help),
                contentDescription = "kundeservice",
                tint = Color.White
            )
        }
        IconButton(modifier = Modifier.padding(horizontal = 20.dp)
                then(Modifier.size(40.dp)),
            onClick = {
                navController.navigate("omOss")
            }) {
            Icon(
                painter = painterResource(id = R.drawable.oss),
                contentDescription = "om Oss",
                tint = Color.White
            )
        }
        IconButton(modifier = Modifier.padding(horizontal = 20.dp)
                then(Modifier.size(40.dp)),
            onClick = {
                navController.navigate("produkter")
            }) {
            Icon(
                painter = painterResource(id = R.drawable.shop),
                contentDescription = "produkter",
                tint = Color.White
            )
        }
    }
}