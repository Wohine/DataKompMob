package com.example.datakompgaming.produkt

import android.annotation.SuppressLint
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
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
import com.example.datakompgaming.screen.printBotBarIcon
import com.example.datakompgaming.screen.printTopBarIcon
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.Console
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

                TextField(
                    label = { Text(text = "Kategori") },
                    value = kategori.value,
                    onValueChange = { kategori.value = it }
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
                    label = { Text(text = "Beskrivelse av produktet") },
                    value = tilstand.value,
                    onValueChange = { tilstand.value = it },
                )

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
                                    Log.d(ContentValues.TAG, downloadUri.toString())
                                    // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                                    // ...
                                }
                            }

                            var getDownloadUrl: Task<Uri>? = null

                            if(imageControl > 0)
                                getDownloadUrl = storageRef.child("images/" + produktNavn.value.text).downloadUrl

                            val produktNavnString = produktNavn.value.text
                            val kategoriString = kategori.value.text
                            val produsentString = produsent.value.text
                            val prisString = pris.value.text
                            val tilstandString = tilstand.value.text
                            val varebeholdningString = "1"
                            val bildeString = getDownloadUrl.toString()


                            data class BruktProdukt(
                                val tittel: String? = null,
                                val kategori: String? = null,
                                val produsent: String? = null,
                                val pris: String? = null,
                                val tilstand: String? = null,
                                val varebeholdning: String? = null,
                                val bildeAdresse: String? = null
                            )

                            val bruktProdukt = BruktProdukt(
                                produktNavnString,
                                kategoriString,
                                produsentString,
                                prisString,
                                tilstandString,
                                varebeholdningString,
                                bildeString,
                            )


                            firebaseAuth.currentUser?.let { it1 ->
                                firestore.collection("Produkter").document("BrukteProdukter").collection(kategoriString).document(Math.random().toString())
                                    .set(
                                        bruktProdukt
                                    )
                                    .addOnSuccessListener { Toast.makeText(cont, "Produktet ditt er sendt inn!", Toast.LENGTH_LONG).show()}
                                    .addOnFailureListener { Toast.makeText(cont, "Noe gikk galt ved innsending av produktet", Toast.LENGTH_LONG).show() }
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


