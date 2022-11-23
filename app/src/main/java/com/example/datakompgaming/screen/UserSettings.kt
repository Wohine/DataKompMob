package com.example.datakompgaming.screen

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.R
import com.example.datakompgaming.ui.theme.DataKompGamingTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun UserSettings(navController: NavController)
{
    var firebaseAuth = FirebaseAuth.getInstance()

    val navn = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val tema = remember { mutableStateOf(TextFieldValue()) }
    val hjelptxt = remember { mutableStateOf(TextFieldValue()) }


    DataKompGamingTheme{
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
                        // .background(Color.White)
                        .verticalScroll(rememberScrollState(), enabled = true),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Spacer(modifier = Modifier.height(40.dp))
                    Image(
                        painter = painterResource(R.drawable.datakomplogo),
                        contentDescription = null
                    )

                    Text(
                        text = "Din bruker: " + firebaseAuth.currentUser?.email,
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    )


                    Column(

                    ) {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Hva lurer du på?", style = TextStyle(fontSize = 10.sp))

                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Hva lurer du på?", style = TextStyle(fontSize = 10.sp))

                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Hva lurer du på?", style = TextStyle(fontSize = 10.sp))

                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Hva lurer du på?", style = TextStyle(fontSize = 10.sp))
                    }
/*
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        //verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(modifier = Modifier
                            .padding(10.dp, 10.dp, 10.dp, 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("Bestilling")
                                },
                                shape = RoundedCornerShape(30.dp),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(60.dp),
                            ) {
                                Text(text = "Bruker instillinger",
                                    textAlign = TextAlign.Center)
                            }
                        }


                        Box(modifier = Modifier
                            .padding(10.dp, 10.dp, 10.dp, 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("Bestilling")
                                },
                                shape = RoundedCornerShape(30.dp),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(60.dp)
                            ) {
                                Text(text = "Bestilling historikk",
                                    textAlign = TextAlign.Center)
                            }
                        }

                        Box(modifier = Modifier
                            .padding(10.dp, 10.dp, 10.dp, 10.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("Bestilling")
                                },
                                shape = RoundedCornerShape(30.dp),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(60.dp)
                            ) {
                                Text(text = "B3",
                                    textAlign = TextAlign.Center)
                            }
                        }
                    }

 */

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
    }
}