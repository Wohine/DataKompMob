package com.example.datakompgaming.bruktProdukt

import android.content.Context
import android.widget.Toast
import com.example.datakompgaming.produkt.firestore
import com.example.datakompgaming.screen.chat.firebaseAuth

/**
 * Laster opp data fra bruktProduktSkjema som er bundet til data klassen opprettet i ProduktSkjemaData.kt.
 * Legger dette i et dokument i collection som samsvarer med kundens oppgitte kategori.
 * Sender en Toast med resultat av opplastningen.
 * @param bruktProdukt
 * @param cont
 */
fun sendSkjemaDB(bruktProdukt: BruktProdukt, cont: Context ){
    firebaseAuth.currentUser?.let { it1 ->
        firestore.collection("Produkter").document("BrukteProdukter").collection(bruktProdukt.kategori).document(Math.random().toString())
            .set(
                bruktProdukt
            )
            .addOnSuccessListener { Toast.makeText(cont, "Produktet ditt er sendt inn!", Toast.LENGTH_LONG).show()}
            .addOnFailureListener { Toast.makeText(cont, "Noe gikk galt ved innsending av produktet", Toast.LENGTH_LONG).show() }
    }
}