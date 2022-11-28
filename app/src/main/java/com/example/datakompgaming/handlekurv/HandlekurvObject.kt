package com.example.datakompgaming.handlekurv

import com.example.datakompgaming.bruktProdukt.BrukteProdukterFire
import com.example.datakompgaming.produkt.ProdukterFire

// lister av brukte og nye produkter i handlekurv
object HandlekurvObject {
    var handlekurvListe = mutableListOf<ProdukterFire>()
    var BruktHandleliste = mutableListOf<BrukteProdukterFire>()
}