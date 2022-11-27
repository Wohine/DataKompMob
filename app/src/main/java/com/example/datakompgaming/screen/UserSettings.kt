package com.example.datakompgaming.screen

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.R
import com.example.datakompgaming.brukerSider.BrukerDataFire
import com.example.datakompgaming.produkt.firestore
import com.example.datakompgaming.ui.theme.DataKompGamingTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.*

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun UserSettings(navController: NavController)
{
    var firebaseAuth = FirebaseAuth.getInstance()



    DataKompGamingTheme{
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        )
        {
            Scaffold(
                topBar = {
                    printTopBarIcon(navController = navController)
                },
                bottomBar = {
                    printBotBarIcon(navController = navController, 2)

                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState(), enabled = true),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    var bruker = BrukerDataFire()

                    val fNavn = remember { mutableStateOf(TextFieldValue()) }
                    val eNavn = remember { mutableStateOf(TextFieldValue()) }
                    val email = remember { mutableStateOf(TextFieldValue()) }
                    val adresse = remember { mutableStateOf(TextFieldValue()) }
                    val postNr = remember { mutableStateOf(TextFieldValue()) }


                    var cont = LocalContext.current

                    Spacer(modifier = Modifier.height(40.dp))
                    Image(
                        painter = painterResource(R.drawable.datakomplogo),
                        contentDescription = null
                    )

                    Text(
                        text = "Din bruker: " + firebaseAuth.currentUser?.email,
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    )


                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        label = { Text(text = "Fornavn") },
                        value = fNavn.value,
                        onValueChange = { fNavn.value = it },
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        label = { Text(text = "Etternavn") },
                        value = eNavn.value,
                        onValueChange = { eNavn.value = it },
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        label = { Text(text = "Email") },
                        value = email.value,
                        onValueChange = { email.value = it },
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        label = { Text(text = "Gate adresse") },
                        value = adresse.value,
                        onValueChange = { adresse.value = it },
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        label = { Text(text = "Postnummer") },
                        value = postNr.value,
                        onValueChange = { postNr.value = it },
                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)) {
                        Button(
                            onClick = {

                                val fNavnString = fNavn.value.text
                                val eNavnString = eNavn.value.text
                                val emailString = email.value.text
                                val adresseString = adresse.value.text
                                val postNrString = postNr.value.text

                                data class BrukerData(
                                    val fornavn: String? = null,
                                    val etternavn: String? = null,
                                    val email: String? = null,
                                    val adresse: String? = null,
                                    val postnummer: String? = null,
                                )

                                val brukerData = BrukerData(
                                    fNavnString,
                                    eNavnString,
                                    emailString,
                                    adresseString,
                                    postNrString,
                                )


                                firebaseAuth.currentUser?.let { it1 ->
                                    firestore.collection("users").document(firebaseAuth.currentUser!!.uid.toString()).collection("Brukerdokumenter").document("Brukerdata")
                                        .set(
                                            brukerData
                                        )
                                        .addOnSuccessListener { Toast.makeText(cont, "Brukerdata oppdatert", Toast.LENGTH_LONG).show() }
                                        .addOnFailureListener { Toast.makeText(cont, "Error, brukerdata ikke oppdatert", Toast.LENGTH_LONG).show() }
                                }

                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .width(300.dp)
                                .height(50.dp)
                        ) {
                            Text(text = "Lagre endringer")
                        }
                    }
                }
            }
        }
    }
}

fun getUserData()
{
    var firebaseAuth = FirebaseAuth.getInstance()


    var docRef = firestore.collection("users").document(firebaseAuth.currentUser!!.uid.toString())
        .collection("Brukerdokumenter").document("Brukerdata")
    var source = Source.DEFAULT

    docRef.get(source).addOnSuccessListener { document ->

        var ud = BrukerDataFire(
            fornavn = document["fornavn"].toString(),
            etternavn = document["etternavn"].toString(),
            email = document["email"].toString(),
            adresse = document["adresse"].toString(),
            postnummer = document["postnummer"].toString(),
        )
    }
}