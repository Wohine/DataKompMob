package com.example.datakompgaming.screen.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.datakompgaming.R
import com.example.datakompgaming.screen.printBotBarIcon
import com.example.datakompgaming.screen.printTopBarIcon
import com.example.datakompgaming.ui.theme.DataKompGamingTheme
import java.text.SimpleDateFormat
import java.util.*

val message = mutableStateOf("")
private val BotChatBubbleShape = RoundedCornerShape(0.dp,8.dp,8.dp,8.dp)
private val AuthorChatBubbleShape = RoundedCornerShape(8.dp,0.dp,8.dp,8.dp)

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
                TopProfile(
                    username = "Test",
                    profile = painterResource(id = R.drawable.floppa),
                    isOnline = true
                )

                ChatSection(Modifier.weight(1f))
                MessageSection()
            }
        }
    }
}

@Composable
fun TopProfile(
    username: String,
    profile: Painter,
    isOnline: Boolean
) {
    Column() {

    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
        // TODO: farge på card og elevation?
    ) {
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

@Composable
fun ChatSection(
    modifier: Modifier = Modifier
) {
    val SimpleDateFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
    LazyColumn(
        modifier = Modifier
//            .fillMaxSize()
            .padding(16.dp),
        reverseLayout = true
    ) {
        items(messageTest) { chat -> 
            MessageItem(
                messageText = chat.text,
                time = SimpleDateFormat.format(chat.time),
                isOut = chat.isOut
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MessageItem(
    messageText: String?,
    time: String,
    isOut: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
    ) {
        if (messageText != null) {
            if (messageText != "") {
                Box(
                    modifier = Modifier
                        .background(
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
            text = time,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageSection() {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        // TODO: farge på card og elevation?
    ) {
        OutlinedTextField(
            placeholder = {
                Text(text = "Message..")
            },
            value = message.value,
            onValueChange = {
                message.value = it
            },
            shape = RoundedCornerShape(25.dp),
            trailingIcon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.floppa),
//                    contentDescription = null,
//                    tint = MaterialTheme.colorScheme.primary,
//                    modifier = Modifier.clickable {}
//                )
            },
            modifier = Modifier
                .padding(10.dp),
        )
    }
}

@Preview
@Composable
fun defaultPre() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopProfile(
            username = "Test",
            profile = painterResource(id = R.drawable.floppa),
            isOnline = true
        )

        ChatSection(Modifier.weight(1f))
        MessageSection()
    }
}




