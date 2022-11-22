package com.example.datakompgaming.produkt

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.datakompgaming.R
import com.example.datakompgaming.screen.printBotBarIcon
import com.example.datakompgaming.screen.printTopBarIcon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable



fun bruktProduktSkjema(navController: NavController) {
    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var firebaseAuth = FirebaseAuth.getInstance()

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
                val produktNavn = remember {
                    mutableStateOf(TextFieldValue())
                }

                val produsent = remember {
                    mutableStateOf(TextFieldValue())
                }

                val pris = remember {
                    mutableStateOf(TextFieldValue())
                }

                val tilstand = remember {
                    mutableStateOf(TextFieldValue())
                }

                val pickedImage = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
                    // process eith the received image uri
                }

                var selectImages by remember { mutableStateOf(listOf<Uri>()) }
                val galleryLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
                        selectImages = it
                    }

                Spacer(modifier = Modifier.height(20.dp))

                Image(painter = painterResource(R.drawable.datakomplogo), contentDescription = null)

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Send inn ditt brukte produkt!",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Center)
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "Produktnavn") },
                    value = produktNavn.value,
                    onValueChange = { produktNavn.value = it }
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "Produsent") },
                    value = produsent.value,
                    onValueChange = { produsent.value = it },
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "Ønsket pris") },
                    value = pris.value,
                    onValueChange = { pris.value = it },
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "Produktets tilstand") },
                    value = tilstand.value,
                    onValueChange = { tilstand.value = it },
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = { galleryLauncher.launch("image/*") },
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(10.dp)
                ) {
                    Text(text = "Last opp bilde fra galleri")
                }

                Spacer(modifier = Modifier.height(15.dp))

                Spacer(modifier = Modifier.height(15.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {

                            val produktNavnString = produktNavn.value.text
                            val produsentString = produsent.value.text
                            val prisString = pris.value.text
                            val tilstandString = tilstand.value.text

                            data class BruktProdukt(
                                val produktNavn: String? = null,
                                val produsent: String? = null,
                                val pris: String? = null,
                                val tilstand: String? = null
                            )

                            val bruktProdukt = BruktProdukt(
                                produktNavnString,
                                produsentString,
                                prisString,
                                tilstandString
                            )

                            firebaseAuth.currentUser?.let { it1 ->
                                firestore.collection("users").document(it1.uid).collection("bruktProduktSkjema").document(Math.random().toString())
                                    .set(
                                        bruktProdukt
                                    )
                                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                                    .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
                            }

                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Send inn")
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
                
                Text(text = "Du er ikke forpliktet til å sende inn produktet ved å sende inn dette skjemaet.", textAlign = TextAlign.Center)

                Spacer(modifier = Modifier.height(100.dp))
            }

        }
    }

}


