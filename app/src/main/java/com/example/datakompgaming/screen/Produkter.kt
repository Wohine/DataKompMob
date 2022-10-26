package com.example.datakompgaming.screen

import android.annotation.SuppressLint
import androidx.navigation.NavController
import com.example.datakompgaming.R

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun Produkter(navController: NavController) {
    Scaffold(bottomBar = {
        printBotBar(navController = navController)
    }) {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState(),enabled = true),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Title("Produkter")
                        Rad("Hovedkort",Color(0xFF82d0d9))
                        Rad("Skjermkort",Color(0xFFd9a932))
                        Rad("Prosessorer",Color(0xFFbd4fdb))
                        Rad("*Eventuelle andre ting*",Color(0xFF6dd6ac))
                        Rad("Jørans syltetøyutvalg",Color(0xFFd18888))
                        
                        Spacer(modifier = Modifier.height(100.dp))
                    }

                }
            }
        }

@Composable
fun Title(name: String) {
    Text(
        text = name,
        fontSize = 35.sp,
        modifier = Modifier
            .fillMaxSize()
            .absolutePadding(bottom = Dp(30f))
            .background(Color(0xFF82d0d9)),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif

    )
}

@Composable
fun KortLabel(tittel: String) {
    Text(
        text = tittel,
        modifier = Modifier
            .absolutePadding(bottom = Dp(5f))
            .background(Color.Transparent),
        textAlign = TextAlign.Right,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color(0xFFf7f7f7)
    )
}

@Composable
fun Kort(tittel: String,pris: String,igjen: String, imagePainter: Painter, farge: Color) {
    Card (
        modifier = Modifier
            .width(300.dp)
            .height(150.dp)
            .absolutePadding(right = Dp(35f))
            .clickable { println("Clicked") },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = farge,

        ) {
        Row() {
            Image(painter = imagePainter, contentDescription = "$tittel",
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                alignment = Alignment.CenterStart
            )
            Column(modifier = Modifier
                .padding(horizontal = 19.dp)
                .fillMaxHeight()
                .background(Color.Transparent),

                ) {
                KortLabel(tittel)
                KortLabel("Pris: $pris"+"kr")
                KortLabel("Kun $igjen igjen!")
            }

        }

    }
}

@Composable
fun Rad(tittel: String, farge: Color) {
    Text(
        text = tittel,
        modifier = Modifier
            .fillMaxSize()
            .absolutePadding(bottom = Dp(10f)),
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        textAlign = TextAlign.Center
    )
    Row(modifier = Modifier
        .height(150.dp)
        .horizontalScroll(rememberScrollState(), enabled = true),
    ) {
        Kort("Test Vare","500","1", painterResource(id = R.drawable.datakomplogo), farge)
        Kort("Test Vare 2","250","3", painterResource(id = R.drawable.datakomplogo), farge)
        Kort("Test Vare 3","750","2", painterResource(id = R.drawable.datakomplogo), farge)
        Kort("Test Vare 4","1250","1", painterResource(id = R.drawable.datakomplogo), farge)
        Kort("Test Vare 5","2750","5", painterResource(id = R.drawable.datakomplogo), farge)
    }
}