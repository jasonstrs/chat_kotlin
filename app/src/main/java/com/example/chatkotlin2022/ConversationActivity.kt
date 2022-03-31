package com.example.chatkotlin2022

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Spinner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConversationActivity : AppCompatActivity() {
    var gs: GlobalState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversations)
        gs = getApplication() as GlobalState?
        val bdl: Bundle? = intent.extras
        // gs?.alerter("id : " + bdl?.getString("id"))
        // gs?.alerter("hash : " + bdl?.getString("hash"))

        val id: String? = bdl?.getString("id")
        val hash: String? = bdl?.getString("hash")
        val apiClient = APIClient()
        val apiService: APIInterface = apiClient.getClient()!!.create(APIInterface::class.java)
        val call1: Call<ListMessages> = apiService.doGetListMessage(hash, id)

        call1.enqueue(object : Callback<ListMessages?> {
            override fun onResponse(call: Call<ListMessages?>?, response: Response<ListMessages?>) {
                val listeMsg: ListMessages? = response.body()
                Log.i(gs?.CAT, listeMsg?.getMessages().toString())

            }

            override fun onFailure(call: Call<ListMessages?>, t: Throwable) {
                TODO("Not yet implemented")
                gs?.alerter("FAILURE")
            }
        })
    }
}