package com.example.datakompgaming.screen.chat

import java.util.Calendar

data class Message(
    var text: String?=null,
    var recipientID: String,
    var time: Long = Calendar.getInstance().timeInMillis,
    var isOut: Boolean = false
)

val messageTest = listOf(
    Message(
        text = "great!",
        recipientID = "Vegard",
        isOut = false
    ),
    Message(
        text = "Jeg har det bra",
        recipientID = "Jøran",
        isOut = true
    ),
    Message(
        text = "Hvordan har du det?",
        recipientID = "Vegard",
        isOut = false
    ),
    Message(
        text = "Hallo :D",
        recipientID = "Jøran",
        isOut = true
    ),
)

