package com.example.datakompgaming.bestillingfiler

data class Order(
    val id: Int,
    val dato: String,
    val key: String,
    val liste: List<Produkt>,
    val totalPris: String
)
