package com.example.datakompgaming.produkt

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.R
import com.example.datakompgaming.bruktProdukt.BruktProdukt
import com.example.datakompgaming.bruktProdukt.sendSkjemaDB
import com.example.datakompgaming.screen.printBotBarIcon
import com.example.datakompgaming.screen.printTopBarIcon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.IOException


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@ExperimentalMaterial3Api
@Composable
fun bruktProduktSkjema(navController: NavController) {

        Scaffold(
            bottomBar = {
                printBotBarIcon(navController = navController, 2)
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

                val kategori = remember {
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

                var imageUri by remember { mutableStateOf<Uri?>(null) }

                var firebaseAuth = FirebaseAuth.getInstance()

                var storage = Firebase.storage
                val storageRef = storage.reference

                var cont = LocalContext.current

                val imagesRef = storageRef.child("images/" + produktNavn.value.text)

                val imageFolderRef = storageRef.child("images/")

                var isImageChosen = false

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri: Uri? ->
                    run {
                        if (uri != null) {
                            imageUri = uri
                            isImageChosen = true
                        }
                    }
                }

                var urlResult = ""

                var imageControl = 0

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


                var expanded by remember { mutableStateOf(false) }
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed: Boolean by interactionSource.collectIsPressedAsState()

                var enabled by rememberSaveable{ mutableStateOf(true)}
                var textKat by remember { mutableStateOf("Kategori") }


                if (isPressed) {
                    expanded = true
                }
                Box(){
                    TextField(
                        readOnly = true,
                        label = { Text(text = "Kategori") },
                        value = textKat,
                        onValueChange = { textKat = it },
                        interactionSource = interactionSource
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Hovedkort") },
                            onClick = { textKat = "Hovedkort"},
                        )
                        DropdownMenuItem(
                            text = { Text("Prossesorer") },
                            onClick = { textKat = "Prossesorer" },
                        )
                        DropdownMenuItem(
                            text = { Text("Skjermkort") },
                            onClick = { textKat = "Skjermkort" },
                        )
                    }
                }


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
                    label = { Text(text = "Beskrivelse av produktet") },
                    value = tilstand.value,
                    onValueChange = { tilstand.value = it },
                )

                Spacer(modifier = Modifier.height(15.dp))


                Spacer(modifier = Modifier.height(15.dp))

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        if(Build.VERSION.SDK_INT >= 29) {
                            try {
                                launcher.launch("image/*")
                            }

                            catch (ex: IOException) {
                                Log.i("Catch", ex.toString())
                            }

                        }
                        imageControl = 1
                    },
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

                            val imagePFD: ParcelFileDescriptor? =
                                imageUri?.let { it1 ->
                                    cont.contentResolver.openFileDescriptor(
                                        it1, "r"
                                    )
                                }

                            if (imagePFD != null) {
                                val bitmap =
                                    BitmapFactory.decodeFileDescriptor(imagePFD.fileDescriptor)

                                val byteArrayOutputStream = ByteArrayOutputStream()

                                bitmap.compress(
                                    Bitmap.CompressFormat.JPEG,
                                    100,
                                    byteArrayOutputStream
                                )

                                val data = byteArrayOutputStream.toByteArray()

                                var uploadTask = imagesRef.putBytes(data)

                                uploadTask.addOnFailureListener {
                                    Log.d(ContentValues.TAG, "Feilet bildeopplastning!")
                                }.addOnSuccessListener { taskSnapshot ->
                                    Log.d(ContentValues.TAG, "Suksessfull bildeopplastning!")
                                    val downloadUri = uploadTask.result
                                    Log.d(ContentValues.TAG, "hei" + downloadUri.toString())

                                }
                            }

                            val bruktProdukt = BruktProdukt(
                                produktNavn.value.text,
                                textKat,
                                produsent.value.text,
                                pris.value.text,
                                tilstand.value.text,
                                "1",
                                "https://firebasestorage.googleapis.com/v0/b/datakompkotlin.appspot.com/o/images%2F"+produktNavn.value.text+"?alt=media",
                            )

                            sendSkjemaDB(bruktProdukt, cont)
                            BrukteProdukterUthentingDB()
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


