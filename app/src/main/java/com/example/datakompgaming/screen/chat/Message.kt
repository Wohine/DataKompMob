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
/**
 * variablen holder styr på hvor mange melding elementer som er i databasen
 */
public var size: Long = 0
/**
 * Data class som inneholder meldings informasjonen
 */
data class Message(
    var text: String = "",
    var sender: String = "",
    var uid: String = "",
    var time: Long = 0,
)
/**
 * Oppretter en Viewmodel, slik at datamodellen kan bli brukt i Chat.kt
 */
class MessageViewModel : ViewModel() {
    private val database =
        Firebase.database("https://datakompkotlin-default-rtdb.europe-west1.firebasedatabase.app")

    private var _messages = mutableStateOf<List<Message>>(emptyList())
    val messages: State<List<Message>> = _messages
    /**
     * getMessages må kjøres for å starte uthenting fra databasen og realtime opdateringer til datamodellen.
     * måtte instansiere en tom variabel med retur verdi eller så funket ikke koden
     */
    var returVariabel = getMessages()

    private fun getMessages(): Int {
        /**
         * //velger dokumentet "messages" i databasen
         */
        database.getReference("messages")
            /**
             * legger til en evenlistener til endringer i database snapshots
             */
            .addValueEventListener(
                object : ValueEventListener {
                    /**
                     * denne metoden kjører for hver endring som skjer i databasen
                     */
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        /**
                         * henter ut database dataen og plaserer det i en liste
                         */
                        _messages.value = dataSnapshot.getValue<List<Message>>()!!
                        /**
                         * size får verdien sin fra antall children i datasnapshotten
                         */
                        size = dataSnapshot.childrenCount
                    }
                    /**
                     * kjører hvis noe går galt
                     */
                    override fun onCancelled(error: DatabaseError) {
                        /**
                         * sender feilmelding i konsoll
                         */
                        Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                    }
                }
            )
        /**
         * retur verdi slik at metoden kan kjøre
         */
        return 2
    }
}

