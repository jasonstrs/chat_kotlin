package com.example.chatkotlin2022

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
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
                val listView : ListView = findViewById(R.id.conversation_svMessages)

                val adapter: ArrayAdapter<Message> = object : ArrayAdapter<Message>(
                    this@ConversationActivity,
                    android.R.layout.simple_list_item_activated_1,
                    listeMsg?.getMessages()?.toTypedArray() as Array<Message>
                ) {
                    override fun getView(
                        position: Int,
                        convertView: View?,
                        parent: ViewGroup
                    ): View {
                        val view = super.getView(position, convertView, parent)
                        val textView = view.findViewById<View>(android.R.id.text1) as TextView

                        textView.setTextColor(Color.parseColor(listeMsg?.getColorWith(position)))
                        return view
                    }
                }
                listView.adapter = adapter
            }

            override fun onFailure(call: Call<ListMessages?>, t: Throwable) {
                gs?.alerter("FAILURE")
                call.cancel()
            }
        })
    }
}