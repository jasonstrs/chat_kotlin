package com.example.chatkotlin2022

interface APIInterface {
    @GET("conversations")
    fun doGetListConversation(@Header("hash") hash: String?): Call<ListConversations?>?
}