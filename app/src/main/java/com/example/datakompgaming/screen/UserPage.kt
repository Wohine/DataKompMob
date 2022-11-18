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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.R
import com.example.datakompgaming.ui.theme.DataKompGamingTheme

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun UserPage(navController: NavController)
{
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

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        //verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)) {
                            Button(
                                onClick = {
                                    navController.navigate("Bestilling")
                                },
                                shape = RoundedCornerShape(30.dp),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(40.dp)
                            ) {
                                Text(text = "B1")
                            }
                        }

                        Box(modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)) {
                            Button(
                                onClick = {
                                    navController.navigate("Bestilling")
                                },
                                shape = RoundedCornerShape(30.dp),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(40.dp)
                            ) {
                                Text(text = "B2")
                            }
                        }

                        Box(modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)) {
                            Button(
                                onClick = {
                                    navController.navigate("Bestilling")
                                },
                                shape = RoundedCornerShape(30.dp),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(60.dp)
                            ) {
                                Text(text = "Bruker instillinger")
                            }
                        }





                    }




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