package com.example.chatkotlin2022

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface APIInterface {
    @GET("conversations")
    fun doGetListConversation(@Header("hash") hash: String?): Call<ListConversations>

    @GET("conversations/{id}/messages")
    fun doGetListMessage(@Header("hash") hash: String?, @Path("id") id: String?): Call<ListMessages>
}