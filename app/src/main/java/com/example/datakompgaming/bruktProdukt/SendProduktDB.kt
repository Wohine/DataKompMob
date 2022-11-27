package com.example.datakompgaming.bruktProdukt

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.datakompgaming.produkt.BrukteProdukterUthentingDB
import com.example.datakompgaming.produkt.ProdukterUthentingDB
import com.example.datakompgaming.produkt.firestore
import com.example.datakompgaming.screen.chat.firebaseAuth

fun SendProduktDB(bruktProdukt: BruktProdukt, cont: Context, navController: NavController){
    var kategori = "annet"
    if (bruktProdukt.kategori != null)
        kategori = bruktProdukt.kategori

    firebaseAuth.currentUser?.let { it1 ->
        firestore.collection("Produkter").document("BrukteProdukter").collection(kategori).document(Math.random().toString())
            .set(
                bruktProdukt
            )
            .addOnSuccessListener {
                BrukteProdukterUthentingDB()
                navController.navigate("bruktProduktSkjema")
                Toast.makeText(cont, "Produktet ditt er sendt inn!", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { Toast.makeText(cont, "Noe gikk galt ved innsending av produktet", Toast.LENGTH_LONG).show() }
    }
}