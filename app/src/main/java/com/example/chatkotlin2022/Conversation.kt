package com.example.chatkotlin2022

class Conversation {
    private var id: String? = null
    private var active: String? = null
    private var theme: String? = null

    @Override
    override fun toString(): String {
        return "Conversation{" +
                "id='" + id + '\'' +
                ", active='" + active + '\'' +
                ", theme='" + theme + '\'' +
                '}'
    }

    fun getActive(): Boolean {
        return active.equals("1")
    }

    fun getTheme(): String? {
        return theme
    }

}