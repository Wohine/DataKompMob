package com.example.datakompgaming.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Kundeservice(navController: NavController) {
    Scaffold(
        bottomBar = {
        printBotBarIcon(navController = navController)
        },
        topBar = {
            printTopBarIcon(navController = navController)
        }
    ) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState(), enabled = true),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {

            val navn = remember { mutableStateOf(TextFieldValue()) }
            val email = remember { mutableStateOf(TextFieldValue()) }
            val tema = remember { mutableStateOf(TextFieldValue()) }
            val hjelptxt = remember { mutableStateOf(TextFieldValue()) }

            Image(painter = painterResource(R.drawable.datakomplogo), contentDescription = null)


            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Kontakt Oss",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Hva lurer du på?", style = TextStyle(fontSize = 10.sp))

            Spacer(modifier = Modifier.height(20.dp))
            TextField(label = { Text(text = "Ditt navn") },
                value = navn.value,
                onValueChange = { navn.value = it })

            Spacer(modifier = Modifier.height(20.dp))
            TextField(label = { Text(text = "Din email") },
                value = email.value,
                onValueChange = { email.value = it })

            Spacer(modifier = Modifier.height(20.dp))
            TextField(label = { Text(text = "Tema") },
                value = tema.value,
                onValueChange = { tema.value = it })

            Spacer(modifier = Modifier.height(20.dp))
            TextField(label = { Text(text = "Hva kan vi hjelpe deg med?") },
                value = hjelptxt.value,
                onValueChange = { hjelptxt.value = it })

            Spacer(modifier = Modifier.height(25.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Send inn")
                }
            }
        }
    }
}
