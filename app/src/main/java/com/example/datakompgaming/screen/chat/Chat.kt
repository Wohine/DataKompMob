package com.example.datakompgaming.screen.chat

import android.annotation.SuppressLint
import android.content.ContentValues
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.R
import com.example.datakompgaming.screen.printBotBarIcon
import com.example.datakompgaming.screen.printTopBarIcon
import com.example.datakompgaming.ui.theme.DataKompGamingTheme
import java.util.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.childEvents
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

//meldingverdier
val message = mutableStateOf("")
private val BotChatBubbleShape = RoundedCornerShape(0.dp,8.dp,8.dp,8.dp)
private val AuthorChatBubbleShape = RoundedCornerShape(8.dp,0.dp,8.dp,8.dp)

//firebase instans
var firebaseAuth = FirebaseAuth.getInstance()

//firebase API
private val database =
    Firebase.database("https://datakompkotlin-default-rtdb.europe-west1.firebasedatabase.app")


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatApp(navController: NavController) {
    Scaffold(
        bottomBar = {
            printBotBarIcon(navController = navController, 1)
        },
        topBar = {
            printTopBarIcon(navController = navController)
        }
    ) {
        DataKompGamingTheme {
            Column(
                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.SpaceBetween
            ) {
                //tidligere brukt top-profil
//                TopProfile(
//                    username = "Test",
//                    profile = painterResource(id = R.drawable.floppa),
//                    isOnline = true
//                )
//
//                ChatSection(Modifier.weight(1f))
//                MessageSection()
                MessageScreen()
                MessageSection()
            }
        }
    }
}


//viewmodel fra message.kt
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MessageScreen(viewModel: MessageViewModel = viewModel()) {

    //dato format, med pattern som henter 24 timer klokke og dag + måned. hvis vi ville hatt år også er det bare å legge til .YYYY etter MM
    val SimpleDateFormat = SimpleDateFormat("HH:mm,   dd.MM", Locale.ENGLISH)
    //scrollstate verdi blir husket
    val listState = rememberLazyListState()
    //CoroutineScope blir husket
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxHeight(0.845f)
            .padding(16.dp),
        reverseLayout = false


    ) {
        //lager melding objektene i listen fra datasnap uthentingen av realtime databasen, fra message.kt
        val isOut: Boolean = true
        items(viewModel.messages.value) { message ->
            //bruker message verdiene for å fylle parametere
            MessageItem(messageText = message.text, time = SimpleDateFormat.format(message.time), sender = message.sender, userUid = message.uid)
            Spacer(modifier = Modifier.height(8.dp))
            //scroller til siste melding automatisk
            coroutineScope.launch {
                listState.scrollToItem(size.toInt())
            }
            println(isOut)
        }
    }
}


//en ubrukt funksjon som vi brukte i en tidligere ide hvor vi hadde en topp profil for å identifisere hvem du snakket med
@Composable
fun TopProfile(
    username: String,
    profile: Painter,
    isOnline: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.14f)
        // TODO: farge på card og elevation?
    ) {
        Column(modifier = Modifier
            .fillMaxSize()) {
            Spacer(modifier = Modifier.height(43.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = profile,
                contentDescription = null,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = username, fontWeight = FontWeight.SemiBold)
                Text(
                    text = if (isOnline) "Online" else "Offline",
                    fontSize = 12.sp
                )
            }
        }
    }
    }
}

//melding objekt
@Composable
fun MessageItem(
    messageText: String?,
    time: String,
    sender: String,
    userUid: String
) {
    //sjekk for om meldingen er sendt av bruker eller ikke
    var isOut = false
    if (firebaseAuth.currentUser?.uid == userUid){
        isOut = true
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        //alignment for sendt av bruker eller ikke
        horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
    ) {
        Text(
            text = ""+sender,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
        //sjekk for om meldingen er tom eller har innhold
        if (messageText != null) {
            if (messageText != "") {
                //box som "chatboble"
                Box(
                    modifier = Modifier
                        .background(
                            //endrer bakgrunn utifra isOut
                            if (isOut) MaterialTheme.colorScheme.onBackground else Color(0xFF616161),
                            shape = if (isOut) AuthorChatBubbleShape else BotChatBubbleShape
                        )
                        .padding(
                            top = 8.dp,
                            bottom = 8.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    Text(
                        text = messageText,
                        color = Color.White
                    )
                }
            }
        }

        Text(
            text = ""+time,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

//funksjon for sending av melding
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageSection() {
        //tekst felt for inntasting av melding
        OutlinedTextField(
            placeholder = {
                Text(text = "Message..")
            },

            //meldingsverdi
            value = message.value,
            //eldrer meldingsverdien på verdiendring
            onValueChange = {
                message.value = it
            },
            //corner-styling
            shape = RoundedCornerShape(15.dp),
            //send icon, clickable
            trailingIcon = {
                Icon(
                    //henter bildefil
                    painter = painterResource(id = R.drawable.send),
                    contentDescription = null,
                    //setter Material theme på icon
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            //henter size fra Message.kt, variablen blir forklart der
                            val messageId = size
                            //får uid referanse til databasen
                            val messageUid = database.getReference("/messages/${messageId}/uid")
                            //får tekst referanse til databasen
                            val messageTxt = database.getReference("/messages/${messageId}/text")
                            //får sender referanse til databasen
                            val messageSender = database.getReference("/messages/${messageId}/sender")
                            //får time referanse til databasen
                            val messageTime = database.getReference("/messages/${messageId}/time")

                            //setter meldings UID verdi i databasen til innlogget bruker sin UID
                            messageUid.setValue(firebaseAuth.currentUser?.uid)
                            //setter meldingens innhold i databasen til tekstfelt verdien
                            messageTxt.setValue(message.value)
                            //setter navn til meldingen i databasen, enten som gjest eller til emailen til innlogget bruker
                            if (firebaseAuth.currentUser?.email == null){
                                messageSender.setValue("Guest")
                            } else {
                                messageSender.setValue(firebaseAuth.currentUser?.email)
                            }
                            //sender inn millisekunder siden 1970 1 januar som Long til databasen
                            messageTime.setValue(Calendar.getInstance().timeInMillis)
                            //resetter meldingsverdi ved sending
                            message.value = ""
                        }
                )
            },
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(55.dp),
        )

}

//preview brukt under utvikling
@Preview
@Composable
fun defaultPre() {
    Column(
        modifier = Modifier
            .fillMaxSize(0.87f)
            .absolutePadding(top = Dp(35F)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        TopProfile(
            username = "Test",
            profile = painterResource(id = R.drawable.floppa),
            isOnline = true
        )

//        ChatSection(Modifier.weight(1f))
        MessageSection()
    }
}




