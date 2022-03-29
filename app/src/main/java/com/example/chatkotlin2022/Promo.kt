package com.example.chatkotlin2022

class Promo {
    var promo: String? = null
    @SerializedName("enseignants")
    var profs: ArrayList<Enseignant>? = null
}