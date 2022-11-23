package com.example.datakompgaming.screen.chat

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*


data class Message(
    var text: String = "",
    var sender: String = "",
//    var recipientID: String,
    var time: Long = Calendar.getInstance().timeInMillis,
//    var time: Long = 0,
    @field:JvmField
    var isOut: Boolean? = null,
)

class MessageViewModel : ViewModel() {
    private val database =
        Firebase.database("https://datakompkotlin-default-rtdb.europe-west1.firebasedatabase.app")

    private var _messages = mutableStateOf<List<Message>>(emptyList())
    val messages: State<List<Message>> = _messages
    var guh = getMessages()
    private fun getMessages(): Int {
        database.getReference("messages")
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        _messages.value = dataSnapshot.getValue<List<Message>>()!!
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                    }
                }
            )
        return 2
    }
}

