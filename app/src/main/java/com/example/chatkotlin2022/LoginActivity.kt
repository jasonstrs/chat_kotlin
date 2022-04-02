package com.example.chatkotlin2022

import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject


class LoginActivity : BaseActivity(), View.OnClickListener {
    var edtLogin: EditText? = null
    var edtPasse: EditText? = null
    var btnLogin: Button? = null
    var cbRemember: CheckBox? = null
    var gs: GlobalState? = null
    var sp: SharedPreferences? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtLogin = findViewById(R.id.edtLogin)
        edtPasse = findViewById(R.id.edtPasse)
        btnLogin = findViewById(R.id.btnLogin)
        cbRemember = findViewById(R.id.cbRemember)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        gs = getApplication() as GlobalState
        btnLogin?.setOnClickListener(this)
        cbRemember?.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()

        btnLogin?.isEnabled = gs?.verifReseau() ?: false
        // relire les préférences de l'application
        // mettre à jour le formulaire
        if (sp?.getBoolean("remember",false) == true) {
            // on charge automatiquement les champs login/passe
            // on coche la case
            edtLogin?.setText(sp?.getString("login",""));
            edtPasse?.setText(sp?.getString("passe",""));
            cbRemember?.isChecked = true;
        } else {
            // on vide
            edtLogin?.setText("");
            edtPasse?.setText("");
            cbRemember?.isChecked = false;
        }
    }

    override fun onClick(view: View?) {
        var editor = sp?.edit()
        when(view?.id) {
            R.id.btnLogin -> {
                // gs?.alerter("click OK");
                // val reqGET = JSONAsyncTask()
                // reqGET.execute("http://tomnab.fr/fixture/","cle=valeur")
                val reqPOST = PostAsyncTask()
                reqPOST.execute(
                    "http://tomnab.fr/chat-api/authenticate",
                    "user=" + edtLogin!!.text.toString()
                            + "&password=" + edtPasse!!.text.toString()
                )
            }
            R.id.cbRemember -> {
                if (cbRemember?.isChecked == true) {
                    // on sauvegarde tout
                    editor?.putBoolean("remember", true);
                    editor?.putString("login",edtLogin?.getText().toString());
                    editor?.putString("passe",edtPasse?.getText().toString());
                } else {
                    // on oublie tout
                    editor?.putBoolean("remember", false);
                    editor?.putString("login","");
                    editor?.putString("passe","");
                }
            }
        }
        editor?.commit()
    }

    inner class JSONAsyncTask : AsyncTask<String?, Void?, JSONObject?>() {
        // Params, Progress, Result
        override fun onPreExecute() {
            super.onPreExecute()
            Log.i(gs?.CAT, "onPreExecute")
        }

        override fun onPostExecute(result: JSONObject?) {
            Log.i(gs?.CAT, "onPostExecute")
            // parcourir le json reçu et afficher les enseignants
            gs?.alerter(result.toString())
            // Utiliser la librairie gson pour l'afficher
            val gson = GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create()
            gs?.alerter(gson.toJson(result))
            val p = gson.fromJson(result.toString(), Promo::class.java)
            gs?.alerter(p.toString())
            try {
                var s = ""
                val tabProfs = result!!.getJSONArray("enseignants")
                for (i in 0 until tabProfs.length()) {
                    val nextProf = tabProfs.getJSONObject(i)
                    s += (nextProf.getString("prenom") + " "
                            + nextProf.getString("nom") + " ")
                }
                gs?.alerter(s)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        override fun doInBackground(vararg data: String?): JSONObject? {
            // pas d'interaction avec l'UI Thread ici
            // String... data : ellipse
            // data[0] contient le premier argument passé à .execute(...)
            // data[1] contient le second argument passé à .execute(...)

            // {"promo":"2020-2021",
            // "enseignants":[
            // {"prenom":"Mohamed","nom":"Boukadir"},
            // {"prenom":"Thomas","nom":"Bourdeaud'huy"},
            // {"prenom":"Mathieu","nom":"Haussher"},
            // {"prenom":"Slim","nom":"Hammadi"}]}
            Log.i(gs?.CAT, "doInBackground")
            val res: String? = gs?.requeteGET(data[0], data[1])
            return try {
                JSONObject(res)
            } catch (e: JSONException) {
                e.printStackTrace()
                null
            }
        }
    }

    inner class PostAsyncTask : AsyncTask<String?, Void?, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(hash: String) {
            // changer d'activité => ChoixConversations
            if (hash === "") {
                gs?.alerter("Les identifiants saisis sont incorrects")
                return
            }

            val versChoixConv = Intent(this@LoginActivity, ChoixConversationActivity::class.java)
            val bdl = Bundle()
            bdl.putString("hash", hash)
            versChoixConv.putExtras(bdl)
            this@LoginActivity.startActivity(versChoixConv)
        }

        override fun doInBackground(vararg data: String?): String {
            val res: String? = gs?.requetePOST(data[0], data[1])
            // {"version":1.3,"success":true,
            // "status":202,"hash":"efd18c70f94a580d9dc85533ddcd9823"}
            return try {
                val ob = JSONObject(res)
                ob.getString("hash")
            } catch (e: JSONException) {
                e.printStackTrace()
                ""
            }
        }
    }

}