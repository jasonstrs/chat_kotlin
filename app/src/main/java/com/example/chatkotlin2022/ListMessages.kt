package com.example.chatkotlin2022

class ListMessages {
    private var version: String? = null
    private var success: String? = null
    private var status: String? = null
    private var messages: ArrayList<Message> = arrayListOf()

    fun getMessages(): ArrayList<Message> {
        return messages
    }

    fun getColorWith(position: Int) : String? {
        return messages.get(position).couleur
    }

    override fun toString(): String {
        return "ListMessages(version=$version, success=$success, status=$status, messages=$messages)"
    }


}