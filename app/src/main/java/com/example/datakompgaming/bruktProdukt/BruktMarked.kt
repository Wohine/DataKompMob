package com.example.datakompgaming.screen

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
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
import com.example.datakompgaming.bruktProdukt.BrukteProdukterFire
import com.example.datakompgaming.produkt.ProdukterFire


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun BruktMarked(navController: NavController, bruktHovedkortListe: MutableList<BrukteProdukterFire>,
                bruktProsessorListe: MutableList<BrukteProdukterFire>, bruktSkjermkortListe: MutableList<BrukteProdukterFire>) {

    Scaffold(
        bottomBar = {
            printBotBarIcon(navController = navController, 3)
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
                Spacer(modifier = Modifier.height(100.dp))
                Text(text = "BRUKTMARKED", fontSize = 40.sp,color = Color(0xFF0888c4), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(30.dp))
                brukteProdukterRad("Hovedkort",Color(0xFF0888c4), bruktHovedkortListe)
                brukteProdukterRad("Skjermkort",Color(0xFF0888c4), bruktSkjermkortListe)
                brukteProdukterRad("Prosessorer",Color(0xFF0888c4), bruktProsessorListe)

                Spacer(modifier = Modifier.height(100.dp))
            }

        }
    }
}
@Composable
fun brukteProdukterRad(tittel: String, farge: Color, produktListe: MutableList<BrukteProdukterFire>, ) {
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
        .height(200.dp)
        .horizontalScroll(rememberScrollState(), enabled = true),
    ) {
        for (produkt in produktListe){
            Log.d(ContentValues.TAG, "Produktliste ok!")
            BrukteProdukterKort(
                produkt,
                farge
            )
        }
    }
}

@Composable
fun BrukteProdukterKort(produkt: BrukteProdukterFire, farge: Color) {
    var pris = produkt.pris
    var tilstand = produkt.tilstand
    var bilde = produkt.bilde
    var cont = LocalContext.current
    Card (
        modifier = Modifier
            .width(400.dp)
            .height(200.dp)
            .absolutePadding(right = Dp(35f))
            .clickable {
                HandlekurvObject.BruktHandleliste.add(produkt)
                Toast.makeText(cont, "lagt i kurv", Toast.LENGTH_SHORT).show()
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            farge
        )
    ) {
        Row() {
            AsyncImage(
                model = bilde,
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
                KortLabel(produkt.tittel)
                Spacer(modifier = Modifier.height(10.dp))
                KortLabel("Pris: $pris"+"kr")
                Spacer(modifier = Modifier.height(10.dp))
                KortLabel("Beskrivelse:")
                KortLabel(tilstand)
            }

        }

    }
}