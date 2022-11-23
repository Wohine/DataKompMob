package com.example.datakompgaming.handlekurv

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
data class ShippingFire(
    val uid: String,
    val fornavn: String,
    val etternavn: String,
    val mail: String,
    val adresse: String,
    val by: String,
    val postkode : String,
)