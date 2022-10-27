package com.example.datakompgaming.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.R
import com.example.datakompgaming.bestillingfiler.Produkt
import com.example.datakompgaming.ui.theme.Purple700


@Composable
fun printBotBarIcon(navController: NavController, nummer: Int){
    // 0 = hjem, 1 = chat, 2 = kundesevice, 3 = om oss, 4 = produkter
    Row(modifier = Modifier
        .background(Color(0xFF6200FF))
        .fillMaxWidth().padding(horizontal = 17.dp)) {

        IconButton(modifier = Modifier.padding(horizontal = 15.dp)
        then(Modifier.size(45.dp)),
            onClick = {
                navController.navigate("HomePage")
            }) {
            Icon(
                Icons.Default.Home,
                contentDescription = "hjemside",
                Modifier.size(35.dp),
                tint = if(nummer == 0 ) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.secondary
            )
        }
        IconButton(modifier = Modifier.padding(horizontal = 15.dp)
        then(Modifier.size(45.dp)),
            onClick = {
                navController.navigate("chat")
            }) {
            Icon(Icons.Default.Chat,
                contentDescription = "chat",
                Modifier.size(35.dp),
                tint = if(nummer == 1 ) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.secondary
            )
        }
        IconButton(modifier = Modifier.padding(horizontal = 15.dp)
        then(Modifier.size(45.dp)),
            onClick = {
                navController.navigate("kundeservice")
            }) {
            Icon(Icons.Default.Help,
                contentDescription = "kundeservice",
                Modifier.size(35.dp),
                tint = if(nummer == 2 ) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.secondary
            )
        }
        IconButton(modifier = Modifier.padding(horizontal = 15.dp)
                then(Modifier.size(45.dp)),
            onClick = {
                navController.navigate("omOss")
            }) {
            Icon(Icons.Default.Info,
                contentDescription = "om oss ",
                Modifier.size(35.dp),
                tint = if(nummer == 3 ) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.secondary
            )
        }
        IconButton(modifier = Modifier.padding(horizontal = 15.dp)
                then(Modifier.size(45.dp)),
            onClick = {
                navController.navigate("produkter")
            }) {
            Icon(Icons.Default.Shop,
                contentDescription = "produkter",
                Modifier.size(35.dp),
                tint = if(nummer == 4 ) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.secondary
            )
        }
    }
}