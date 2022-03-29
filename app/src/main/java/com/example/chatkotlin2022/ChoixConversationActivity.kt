package com.example.chatkotlin2022

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChoixConversationActivity : AppCompatActivity()  {
    var gs: GlobalState? = null
    var spinConversations: Spinner? = null

    /*class MyCustomAdapter(context: Context?,
                          private val layoutId: Int,
                          objects: ArrayList<Conversation>) : ArrayAdapter<Conversation?>(context!!, layoutId, objects) {
        private val dataConvs: ArrayList<Conversation>

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //return getCustomView(position, convertView, parent);
            // getLayoutInflater() vient de Android.Activity => il faut utiliser une classe interne
            val inflater: LayoutInflater = getLayoutInflater()
            val item: View = inflater.inflate(layoutId, parent, false)
            val nextC: Conversation = dataConvs.get(position)
            val label: TextView = item.findViewById(R.id.spinner_theme) as TextView
            label.setText(nextC.getTheme())
            val icon: ImageView = item.findViewById(R.id.spinner_icon) as ImageView
            if (nextC.getActive()) icon.setImageResource(R.drawable.icon36) else icon.setImageResource(R.drawable.icongray36)
            return item
        }

         override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //return getCustomView(position, convertView, parent);
            val inflater: LayoutInflater = getLayoutInflater()
            val item: View = inflater.inflate(layoutId, parent, false)
            val nextC: Conversation = dataConvs.get(position)
            val label: TextView = item.findViewById(R.id.spinner_theme) as TextView
            label.setText(nextC.getTheme())
            val icon: ImageView = item.findViewById(R.id.spinner_icon) as ImageView
            if (nextC.getActive()) icon.setImageResource(R.drawable.icon) else icon.setImageResource(R.drawable.icongray)
            return item
        }

        init {
            dataConvs = objects
        }
    }*/

    override protected fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_conversation)
        /*gs = getApplication() as GlobalState?
        val bdl: Bundle = this.getIntent().getExtras()
        gs.alerter("hash : " + bdl.getString("hash"))
        val hash: String = bdl.getString("hash")
        val apiService: APIInterface = APIClient.getClient().create(APIInterface::class.java)
        val call1: Call<ListConversations> = apiService.doGetListConversation(hash)
        call1.enqueue(object : Callback<ListConversations?>() {
            override fun onResponse(call: Call<ListConversations?>?, response: Response<ListConversations?>) {
                val listeConvs: ListConversations = response.body()
                Log.i(gs.CAT, listeConvs.toString())
                spinConversations = findViewById(R.id.spinConversations) as Spinner?
                //ArrayAdapter<Conversation> dataAdapter = new ArrayAdapter<Conversation>(
                //        ChoixConversationActivity.this,
                //        android.R.layout.simple_spinner_item,
                //        listeConvs.getConversations());
                //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //spinConversations.setAdapter(dataAdapter);
                spinConversations.setAdapter(MyCustomAdapter(this@ChoixConversationActivity,
                        R.layout.spinner_item,
                        listeConvs.getConversations()))
            }

            override fun onFailure(call: Call<ListConversations?>, t: Throwable?) {
                call.cancel()
            }
        })*/
    }
}