package com.example.datakompgaming.handlekurv

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.google.firebase.firestore.Exclude

data class ShippingFire(
    val uid: String,
    val fornavn: String,
    val etternavn: String,
    val mail: String,
    val adresse: String,
    val by: String,
    val postkode : String,
    val dato: String,
    var totalPris: String,
    val basket: MutableList<Map<String, Any?>> = arrayListOf()
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "fornavn" to fornavn,
            "etternavn" to etternavn,
            "mail" to mail,
            "adresse" to adresse,
            "by" to by,
            "postkode" to postkode,
            "basket" to basket,
            "dato" to dato,
            "totalPris" to totalPris
        )
    }
}