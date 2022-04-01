package com.example.chatkotlin2022

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @GET("conversations")
    fun doGetListConversation(@Header("hash") hash: String?): Call<ListConversations>

    @GET("conversations/{id}/messages")
    fun doGetListMessage(@Header("hash") hash: String?, @Path("id") id: String?): Call<ListMessages>

    @POST("conversations/{id}/messages?")
    fun postMessage(@Header("hash") hash: String?, @Path("id") id: String?, @Query("contenu") message: String?): Call<ListMessages>
}