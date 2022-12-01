package com.example.datakompgaming.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.R
import com.example.datakompgaming.ui.theme.DataKompGamingTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun Kundeservice(navController: NavController) {

    var firebaseAuth = FirebaseAuth.getInstance()
    var cont = LocalContext.current

    DataKompGamingTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        ) {
            Scaffold(
                bottomBar = {
                    printBotBarIcon(navController = navController, 2)
                },
                topBar = {
                    printTopBarIcon(navController = navController)
                }
            ) {

            /**
             * Oppretter column og legger til scrollability
             * Sentrerer elementer i kolonnen
                 */
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState(), enabled = true),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                )
                {

                /**
                 * Oppretter values for å holde på textfield verdiene
                     */
                    val navn = remember { mutableStateOf(TextFieldValue()) }
                    val email = remember { mutableStateOf(TextFieldValue()) }       //fjern email
                    val tema = remember { mutableStateOf(TextFieldValue()) }
                    val hjelptxt = remember { mutableStateOf(TextFieldValue()) }
                    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

                    /**
                     * Spacer for å unngå at top baren overlapper elementer på siden
                     */
                    Spacer(modifier = Modifier.height(20.dp))

                    Image(
                        painter = painterResource(R.drawable.datakomplogo),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Kontakt Oss",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Hva lurer du på?", style = TextStyle(fontSize = 10.sp))

                    /**
                     * Oppretter textfelt angående kundeservice skjema
                     */
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

                    /**
                     * En "send inn" knapp hvor ved klikk sender input verdien til texfletene over
                     * inn i en lokal data klasse. Det kobles så over til nåværende bruker og lages
                     * ett nytt dokument med tilfeldig saksnummer, inni dokumentet lagres saks
                     * informasjonen fra textfeltene i guien
                     */
                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                val navnString = navn.value.text
                                val emailString = email.value.text
                                val temaString = tema.value.text
                                val hjelpString = hjelptxt.value.text

                                data class KundeServiceObjekt(
                                    val navn: String? = null,
                                    val email: String? = null,
                                    val tema: String? = null,
                                    val beskrivelse: String? = null,
                                )

                                val kundeService = KundeServiceObjekt(
                                    navnString,
                                    emailString,
                                    temaString,
                                    hjelpString,
                                )

                                firebaseAuth.currentUser?.let { it1 ->
                                    firestore.collection("users")
                                        .document(firebaseAuth.currentUser!!.uid.toString())
                                        .collection("Kundeservice")
                                        .document("Saksnummer: " + Math.random().toString())
                                        .set(
                                            kundeService
                                        )
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                cont,
                                                "Saken din er sendt inn!",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(
                                                cont,
                                                "Noe gikk galt ved innsending av skjema",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
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
                }
            }
        }
    }
}

