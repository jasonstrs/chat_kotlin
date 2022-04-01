package com.example.chatkotlin2022

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConversationActivity : AppCompatActivity(), View.OnClickListener {
    var edtSaisie: EditText? = null
    var btnEnvoie: Button? = null
    var gs: GlobalState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversations)
        edtSaisie = findViewById(R.id.conversation_edtMessage)
        btnEnvoie = findViewById(R.id.conversation_btnOK)
        btnEnvoie?.setOnClickListener(this)
        gs = getApplication() as GlobalState?

        val bdl: Bundle? = intent.extras
        val id: String? = bdl?.getString("id")
        val hash: String? = bdl?.getString("hash")
        val isActive: Boolean?= bdl?.getBoolean("actif")
        if(!isActive!!)
            btnEnvoie?.setEnabled(false)
        val apiClient = APIClient()
        val apiService: APIInterface = apiClient.getClient()!!.create(APIInterface::class.java)
        val call1: Call<ListMessages> = apiService.doGetListMessage(hash, id)

        call1.enqueue(object : Callback<ListMessages?> {
            override fun onResponse(call: Call<ListMessages?>?, response: Response<ListMessages?>) {
                val listeMsg: ListMessages? = response.body()
                Log.i(gs?.CAT, listeMsg?.getMessages().toString())

            }

            override fun onFailure(call: Call<ListMessages?>, t: Throwable) {
                gs?.alerter("FAILURE")
            }
        })
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.conversation_btnOK -> {
                val bdl: Bundle? = intent.extras
                val id: String? = bdl?.getString("id")
                val hash: String? = bdl?.getString("hash")
                val apiClient = APIClient()
                val apiService: APIInterface = apiClient.getClient()!!.create(APIInterface::class.java)

                val message = edtSaisie!!.text.toString()

                val call2: Call<ListMessages> = apiService.postMessage(hash, id, message)
                call2.enqueue(object : Callback<ListMessages?> {
                    override fun onResponse(call: Call<ListMessages?>?, response: Response<ListMessages?>) {
                        val listeMsg: ListMessages? = response.body()
                        edtSaisie!!.getText().clear()
                    }

                    override fun onFailure(call: Call<ListMessages?>, t: Throwable) {
                        gs?.alerter("FAILURE")
                    }
                })
            }
        }
    }
}