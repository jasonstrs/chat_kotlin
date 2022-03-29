package com.example.chatkotlin2022

class ListConversations {
    private var version: String? = null
    private var success: String? = null
    private var status: String? = null
    private var conversations: ArrayList<Conversation?>? = null

    fun getConversations(): ArrayList<Conversation?>? {
        return conversations
    }

    @Override
    override fun toString(): String {
        return "ListConversations{" +
                "version='" + version + '\'' +
                ", success='" + success + '\'' +
                ", status='" + status + '\'' +
                ", conversations=" + conversations +
                '}'
    }
}