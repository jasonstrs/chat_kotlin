package com.example.chatkotlin2022

class Enseignant {
    private var prenom: String? = null
    private var nom: String? = null

    @Override
    override fun toString(): String {
        return "Enseignant{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                '}'
    }
}