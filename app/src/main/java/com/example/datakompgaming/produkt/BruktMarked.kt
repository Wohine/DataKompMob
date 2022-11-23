package com.example.datakompgaming.screen

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.navigation.NavController
import com.example.datakompgaming.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.datakompgaming.handlekurv.HandlekurvObject
import com.example.datakompgaming.produkt.BrukteProdukterFire
import com.example.datakompgaming.produkt.ProdukterFire


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun BruktMarked(navController: NavController, bruktHovedkortListe: MutableList<BrukteProdukterFire>,
              bruktProsessorListe: MutableList<BrukteProdukterFire>, bruktSkjermkortListe: MutableList<BrukteProdukterFire>) {

    Scaffold(
        bottomBar = {
            printBotBarIcon(navController = navController, 4)
        },
        topBar = {
            printTopBarIcon(navController = navController)
        }
    ) {

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background

        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState(),enabled = true),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Title("Markedsplass")
                brukteProdukterRad("Hovedkort",Color(0xFF82d0d9), bruktHovedkortListe)
                brukteProdukterRad("Skjermkort",Color(0xFF82d0d9), bruktSkjermkortListe)
                brukteProdukterRad("Prosessorer",Color(0xFF82d0d9), bruktProsessorListe)

                Spacer(modifier = Modifier.height(100.dp))
            }

        }
    }
}

@Composable
fun BruktTitle(name: String) {
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
fun BruktKortLabel(tittel: String) {
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
fun BruktKort(tittel: String,pris: String,tilstand: String, imagePainter: Painter) {
    Card (
        modifier = Modifier
            .width(300.dp)
            .height(150.dp)
            .absolutePadding(right = Dp(35f))
            .clickable { println("Clicked") },
        shape = RoundedCornerShape(8.dp),


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
                BruktKortLabel(tittel)
                BruktKort("Pris: $pris"+"kr")
                BruktKort("Tilstand $tilstand ")
            }

        }

    }
}

@Composable
fun BruktRad(tittel: String) {
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
        BruktKort("Test Vare","500","1", painterResource(id = R.drawable.datakomplogo))
        BruktKort("Test Vare 2","250","3", painterResource(id = R.drawable.datakomplogo))
        BruktKort("Test Vare 3","750","2", painterResource(id = R.drawable.datakomplogo))
        BruktKort("Test Vare 4","1250","1", painterResource(id = R.drawable.datakomplogo))
        BruktKort("Test Vare 5","2750","5", painterResource(id = R.drawable.datakomplogo))
    }
}

@Composable
fun brukteProdukterRad(tittel: String, farge: Color, bruktProduktListe: MutableList<ProdukterFire>, ) {
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
        for (produkt in bruktProduktListe){
            Log.d(ContentValues.TAG, "Produktliste ok!")
            BrukteProdukterKort(
                bruktProdukt,
                farge
            )
        }
    }
}





@Composable
fun BrukteProdukterKort(bruktProdukt: BrukteProdukterFire, farge: Color) {
    var tilstand = bruktProdukt.tilstand
    var pris = bruktProdukt.pris
    var cont = LocalContext.current
    Card (
        modifier = Modifier
            .width(300.dp)
            .height(150.dp)
            .absolutePadding(right = Dp(35f))
            .clickable {
                HandlekurvObject.handlekurvListe.add(bruktProdukt)

            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            farge
        )
    ) {
        Row() {
            AsyncImage(
                model = bruktProdukt.bilde,
                contentDescription = "null",
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
                KortLabel(bruktProdukt.tittel)
                KortLabel("Pris: $pris"+"kr")
                KortLabel("Tilstand: $tilstand")
            }

        }

    }
}