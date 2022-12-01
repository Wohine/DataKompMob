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
import com.example.datakompgaming.produkt.ProduktObject
import com.example.datakompgaming.produkt.ProdukterFire
import com.example.datakompgaming.produkt.ProdukterUthentingDB
import com.example.datakompgaming.ui.theme.DataKompGamingBlue


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun Produkter(navController: NavController, hovedListe: MutableList<ProdukterFire>,
            prosesstListe: MutableList<ProdukterFire>, skjermListe: MutableList<ProdukterFire>) {

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
                        Spacer(modifier = Modifier.height(100.dp))
                        Text(text = "NYE PRODUKTER", fontSize = 40.sp,color = Color(0xFF0888c4), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(30.dp))
                        produkterRad("Hovedkort", DataKompGamingBlue, hovedListe)
                        produkterRad("Skjermkort",DataKompGamingBlue, prosesstListe)
                        produkterRad("Prosessorer",DataKompGamingBlue, skjermListe)

                        Spacer(modifier = Modifier.height(100.dp))
                    }

                }
            }
        Log.d(ContentValues.TAG, ""+ ProduktObject.HovedKortListe.size)
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
            .width(200.dp)
            .background(Color.Transparent),
        textAlign = TextAlign.Right,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        color = Color(0xFFf7f7f7)
    )
}


/**
 * Utformer rader for de ulike produktkategoriene.
 */
@Composable
fun produkterRad(tittel: String, farge: Color, produktListe: MutableList<ProdukterFire>, ) {
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
        for (produkt in produktListe){
            Log.d(ContentValues.TAG, "Produktliste ok!")
            ProdukterKort(
                produkt,
                farge
            )
        }
    }
}

/**
 * Utforming av produktkortet for de uthentede produktene fra Firestore
 */
@Composable
fun ProdukterKort(produkt: ProdukterFire, farge: Color) {
    var varebeholdning = produkt.varebeholdning
    var pris = produkt.pris
    var cont = LocalContext.current
    Card (
        modifier = Modifier
            .width(400.dp)
            .height(150.dp)
            .absolutePadding(right = Dp(35f))
            .clickable {
                if (produkt.varebeholdning.toInt() < 1)
                    Toast
                        .makeText(cont, "Utsolgt..", Toast.LENGTH_SHORT)
                        .show()
                else {
                    Toast
                        .makeText(cont, "lagt i kurv", Toast.LENGTH_SHORT)
                        .show()
                    HandlekurvObject.handlekurvListe.add(produkt)
                }

            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            farge
        )
        ) {
        Row() {
            AsyncImage(
                model = produkt.bilde,
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
                KortLabel("Pris: $pris"+"kr")
                KortLabel("Kun $varebeholdning igjen!")
            }

        }

    }
}