package com.example.datakompgaming.handlekurv

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.datakompgaming.screen.*
import com.example.datakompgaming.user
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * viser skjema for å skrive inn shipping adressen sin
 * @param navController
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun printShippingSkjema(navController: NavController) {
    Scaffold(
        bottomBar = {
            printBotBarIcon(navController = navController, 3)
        },
        topBar = {
            printTopBarIcon(navController = navController)
        }
    ) {
        var cont = LocalContext.current

        val fornavn = remember { mutableStateOf(TextFieldValue()) }
        val etternavn = remember { mutableStateOf(TextFieldValue()) }
        val mail = remember { mutableStateOf(TextFieldValue()) }
        val adresse = remember { mutableStateOf(TextFieldValue()) }
        val by = remember { mutableStateOf(TextFieldValue()) }
        val postkode = remember { mutableStateOf(TextFieldValue()) }
        var uID = ""
        if (user?.uid != null) uID = user?.uid!!

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
                Spacer(modifier = Modifier.height(55.dp))
                TextField(
                    label = { Text(text = "fornavn") },
                    value = fornavn.value,
                    onValueChange = { fornavn.value = it }
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    label = { Text(text = "etternavn") },
                    value = etternavn.value,
                    onValueChange = { etternavn.value = it }
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    label = { Text(text = "mail") },
                    value = mail.value,
                    onValueChange = { mail.value = it }
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    label = { Text(text = "adresse") },
                    value = adresse.value,
                    onValueChange = { adresse.value = it }
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    label = { Text(text = "by") },
                    value = by.value,
                    onValueChange = { by.value = it }
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    label = { Text(text = "postkode") },
                    value = postkode.value,
                    onValueChange = { postkode.value = it }
                )
                Button(onClick = {
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm ")
                    val current = LocalDateTime.now().format(formatter)

                    var shipInfo = ShippingFire(
                        uid = uID,
                        fornavn = fornavn.value.text,
                        etternavn = etternavn.value.text,
                        mail = mail.value.text,
                        adresse = adresse.value.text,
                        by = by.value.text,
                        postkode = postkode.value.text,
                        totalPris = pris.toString(),
                        dato = current
                    )

                    for(item in HandlekurvObject.handlekurvListe)
                        shipInfo.basket.add(item.toMap())
                    for(item in HandlekurvObject.BruktHandleliste)
                        shipInfo.basket.add(item.toMap())



                    kjopProdukterDB(shipInfo)
                    updateVarerPaLager()
                    HandlekurvObject.handlekurvListe.clear()
                    HandlekurvObject.BruktHandleliste.clear()
                    navController.navigate("HomePage")
                    Toast.makeText(cont, "takk for ditt kjøp", Toast.LENGTH_SHORT).show()
                    Log.d(ContentValues.TAG, shipInfo.fornavn)
                }) {
                    Text(text = "trykk her")
                }
            }
        }



        }
    }