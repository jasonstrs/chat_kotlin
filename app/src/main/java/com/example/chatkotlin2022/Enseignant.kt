package com.example.chatkotlin2022

class Enseignant {
    var prenom: String? = null
    var nom: String? = null

    @Override
    override fun toString(): String {
        return "Enseignant{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                '}'
    }
}