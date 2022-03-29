package com.example.chatkotlin2022

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface APIInterface {
    @GET("conversations")
    fun doGetListConversation(@Header("hash") hash: String?): Call<ListConversations>
}