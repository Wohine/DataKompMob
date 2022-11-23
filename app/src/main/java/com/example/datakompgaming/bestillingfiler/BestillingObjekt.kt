package com.example.datakompgaming.bestillingfiler

data class Order(
    val id: String,
    val dato: String,
    val key: String,
    val liste: MutableList<Produkt>,
    val totalPris: String
)
