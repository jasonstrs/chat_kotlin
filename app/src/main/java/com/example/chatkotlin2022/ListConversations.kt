package com.example.chatkotlin2022

class ListConversations {
    var version: String? = null
    var success: String? = null
    var status: String? = null
    var conversations: ArrayList<Conversation?>? = null

    @Override
    override fun toString(): String? {
        return "ListConversations{" +
                "version='" + version + '\'' +
                ", success='" + success + '\'' +
                ", status='" + status + '\'' +
                ", conversations=" + conversations +
                '}'
    }


    fun getConversations(): ArrayList<Conversation> {
        return conversations
    }
}