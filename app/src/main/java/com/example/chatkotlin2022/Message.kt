package com.example.chatkotlin2022

class Message {
    // ID / IDConv / IDAut / Contenu
    private var id = 0
    var contenu: String? = null
    var auteur: String? = null
    var couleur: String? = null

    constructor(contenu: String?, auteur: String?) {
        this.contenu = contenu
        this.auteur = auteur
    }


    override fun toString(): String {
        return "$auteur : \n$contenu"
    }
}