package com.example.chatkotlin2022

class Message {
    // ID / IDConv / IDAut / Contenu
    private var id = 0
    var contenu: String? = null
    var auteur: String? = null
    var couleur: String? = null


    override fun toString(): String {
        return "$auteur : $contenu : $id : $couleur"
    }
}