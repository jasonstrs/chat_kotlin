package com.example.chatkotlin2022

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.GsonBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChoixConversationActivity : AppCompatActivity(), View.OnClickListener {
    var gs: GlobalState? = null
    var spinConversations: Spinner? = null
    var btnChoixConv: Button? = null

    class MyCustomAdapter(
        context: Context?,
        private val layoutId: Int,
        objects: ArrayList<Conversation?>?
    ) : ArrayAdapter<Conversation?>(context!!, layoutId, objects!!) {
        private val dataConvs: ArrayList<Conversation?>?

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater: LayoutInflater = LayoutInflater.from(context)
            val item: View = inflater.inflate(layoutId, parent, false)
            val nextC: Conversation? = dataConvs?.get(position)
            val label: TextView = item.findViewById(R.id.spinner_theme) as TextView
            label.setText(nextC?.getTheme())
            val icon: ImageView = item.findViewById(R.id.spinner_icon) as ImageView
            if (nextC != null) {
                if (nextC.getActive()) icon.setImageResource(R.drawable.icon36) else icon.setImageResource(R.drawable.icongray36)
            }
            return item
        }

         override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater: LayoutInflater = LayoutInflater.from(context)
            val item: View = inflater.inflate(layoutId, parent, false)
            val nextC: Conversation? = dataConvs?.get(position)
            val label: TextView = item.findViewById(R.id.spinner_theme) as TextView
             if (nextC != null) {
                 label.setText(nextC.getTheme())
             }
            val icon: ImageView = item.findViewById(R.id.spinner_icon) as ImageView
             if (nextC != null) {
                 if (nextC.getActive()) icon.setImageResource(R.drawable.icon) else icon.setImageResource(R.drawable.icongray)
             }
            return item
        }

        init {
            dataConvs = objects
        }
    }

    override protected fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_conversation)
        gs = getApplication() as GlobalState?
        val bdl: Bundle? = intent.extras
        gs?.alerter("hash : " + bdl?.getString("hash"))
        val hash: String? = bdl?.getString("hash")
        val apiClient = APIClient()
        val apiService: APIInterface = apiClient.getClient()!!.create(APIInterface::class.java)
        val call1: Call<ListConversations> = apiService.doGetListConversation(hash)
        btnChoixConv = findViewById(R.id.btnChoixConv)
        btnChoixConv?.setOnClickListener(this)


        call1.enqueue(object : Callback<ListConversations?> {
            override fun onResponse(call: Call<ListConversations?>?, response: Response<ListConversations?>) {
                val listeConvs: ListConversations? = response.body()
                Log.i(gs?.CAT, listeConvs.toString())
                spinConversations = findViewById(R.id.spinConversations) as Spinner?
                spinConversations?.setAdapter(MyCustomAdapter(this@ChoixConversationActivity,
                        R.layout.spinner_item,
                        listeConvs?.getConversations()))
                btnChoixConv?.isEnabled = true
            }

            override fun onFailure(call: Call<ListConversations?>, t: Throwable?) {
                call.cancel()
            }
        })
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnChoixConv -> {
                // gs?.alerter("Choix Conv")
                // gs?.alerter(spinConversations?.selectedItem.toString())
                val selectedValue = spinConversations?.selectedItem as Conversation
                // gs?.alerter(selectedValue.getId().toString())
                if (selectedValue == null) {
                    gs?.alerter("La selection est incorrecte")
                    return
                }
                val versConv = Intent(this@ChoixConversationActivity, ConversationActivity::class.java)
                val bdl: Bundle? = intent.extras
                bdl?.putString("id", selectedValue.getId())
                gs?.alerter(selectedValue.getId().toString())
                bdl?.putBoolean("actif", selectedValue.getActive())
                bdl?.putString("theme", selectedValue.getTheme())
                versConv.putExtras(bdl!!)
                this@ChoixConversationActivity.startActivity(versConv)
            }
        }
    }
}