package com.example.datakompgaming.brukerSider

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.datakompgaming.screen.printBotBarIcon
import com.example.datakompgaming.screen.printTopBarIcon
import com.example.datakompgaming.ui.theme.DataKompGamingTheme
import com.google.firebase.auth.FirebaseAuth

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun BrukerPassord(navController: NavController) {

    var firebaseAuth = FirebaseAuth.getInstance()
    var cont = LocalContext.current

    DataKompGamingTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        ) {
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

                    val passord = remember { mutableStateOf(TextFieldValue()) }
                    val passordCheck = remember { mutableStateOf(TextFieldValue()) }

                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(R.drawable.datakomplogo),
                        contentDescription = null,
                        Modifier
                            .size(200.dp)
                            .offset(x = 0.dp, y = 20.dp)
                    )

                    Text(
                        text = "Din bruker: " + firebaseAuth.currentUser?.email,
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Endring av passord er ikke nødvendig med google konto eller som gjest",
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            label = { Text(text = "Nytt passord") },
                            value = passord.value,
                            onValueChange = { passord.value = it },
                        )

                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        label = { Text(text = "Gjennta nytt passord") },
                        value = passordCheck.value,
                        onValueChange = { passordCheck.value = it },
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)) {
                        Button(
                            onClick = {
                                      if (passord.value == passordCheck.value){
                                          firebaseAuth.currentUser?.updatePassword(passord.value.text)
                                              ?.addOnSuccessListener { Toast.makeText(cont, "Passord er endret til: " + passord.value.text, Toast.LENGTH_LONG).show() }
                                              ?.addOnFailureListener { Toast.makeText(cont, "Error, passordet er uendret", Toast.LENGTH_LONG).show() }
                                      }

                                else if (passord.value != passordCheck.value)
                                      {
                                          Toast.makeText(cont, "Sjekk at du har skrevet samme passord i begge felt", Toast.LENGTH_LONG).show()
                                      }
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .width(300.dp)
                                .height(50.dp)

                        ) {
                            Text(text = "Endre passord")
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)) {
                        Button(
                            onClick = {
                                      firebaseAuth.sendPasswordResetEmail(firebaseAuth.currentUser!!.email.toString())
                                          .addOnSuccessListener { Toast.makeText(cont, "Nytt passord sendt på mail", Toast.LENGTH_LONG).show() }
                                          .addOnFailureListener { Toast.makeText(cont, "Error, passord er uendret og nytt passord er ikke sendt", Toast.LENGTH_LONG).show() }
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .width(300.dp)
                                .height(50.dp)

                        ) {
                            Text(text = "Send nytt passord på mail")
                        }
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}
