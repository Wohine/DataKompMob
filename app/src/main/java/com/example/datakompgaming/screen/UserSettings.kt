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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.datakompgaming.LogoutPreview
import com.example.datakompgaming.R
import com.example.datakompgaming.brukerSider.BrukerDataFire
import com.example.datakompgaming.mainActivity
import com.example.datakompgaming.produkt.firestore
import com.example.datakompgaming.screen.chat.firebaseAuth
import com.example.datakompgaming.ui.theme.DataKompGamingTheme
import com.example.datakompgaming.ui.theme.Red30
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Source

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")



@Composable
fun UserSettings(navController: NavController)
{
    var firebaseAuth = FirebaseAuth.getInstance()
    var cont = LocalContext.current


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

                    var bruker = getUserData()

                    val fNavn = remember { mutableStateOf(TextFieldValue()) }
                    val eNavn = remember { mutableStateOf(TextFieldValue()) }
                    val adresse = remember { mutableStateOf(TextFieldValue()) }
                    val postNr = remember { mutableStateOf(TextFieldValue()) }

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
                                val adresseString = adresse.value.text
                                val postNrString = postNr.value.text

                                data class BrukerData(
                                    val fornavn: String? = null,
                                    val etternavn: String? = null,
                                    val adresse: String? = null,
                                    val postnummer: String? = null,
                                )

                                val brukerData = BrukerData(
                                    fNavnString,
                                    eNavnString,
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

                    Spacer(modifier = Modifier.height(25.dp))
                    DeleteUserWindow()

                    Spacer(modifier = Modifier.height(40.dp))

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
            adresse = document["adresse"].toString(),
            postnummer = document["postnummer"].toString(),
        )
    }
    //return ud
}




@Composable
fun DeleteUserWindow(){

    val openDialog = remember { mutableStateOf(false) }
    val buttonTitle = remember { mutableStateOf("Slett bruker") }

    var cont = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(

            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            onClick = {
                openDialog.value = !openDialog.value

                if (!openDialog.value) {

                    buttonTitle.value = "Slett bruker"
                }
            }
        ) {
            Text(text = buttonTitle.value, modifier = Modifier.padding(3.dp))
        }

        Box {
            val popupWidth = 300.dp
            val popupHeight = 150.dp

            if (openDialog.value) {
                buttonTitle.value = "Ikke slett bruker!"

                Popup(
                    alignment = Alignment.TopCenter,
                    offset = IntOffset(0, -600),
                    properties = PopupProperties()
                ) {

                    Box(
                        Modifier
                            .size(popupWidth, popupHeight)
                            .padding(top = 5.dp)
                            .background(
                                color = MaterialTheme.colorScheme.error,
                                RoundedCornerShape(10.dp)
                            )
                            .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp))
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            
                            Text(text = "Er du helt sikker på at du vil slette dennebrukeren?",
                                textAlign = TextAlign.Center)

                            Spacer(modifier = Modifier.height(5.dp))
                            
                            Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)) {
                                Button(
                                    onClick = {

                                        firebaseAuth.currentUser?.delete()
                                            ?.addOnSuccessListener { Toast.makeText(cont, "Bruker er slettet", Toast.LENGTH_LONG).show() }
                                            ?.addOnFailureListener { Toast.makeText(cont, "Error, brukeren er ikke slettet", Toast.LENGTH_LONG).show() }
                                        
                                        mainActivity?.logOut()


                                    },
                                    shape = RoundedCornerShape(50.dp),
                                    modifier = Modifier
                                        .width(300.dp)
                                        .height(50.dp)

                                ) {
                                    Text(text = "Slett Bruker")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}