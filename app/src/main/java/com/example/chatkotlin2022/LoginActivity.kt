package com.example.chatkotlin2022

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    var edtLogin: EditText? = null
    var edtPasse: EditText? = null
    var btnLogin: Button? = null
    var cbRemember: CheckBox? = null

    val CAT = "IG2I"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtLogin = findViewById(R.id.edtLogin);
        edtPasse = findViewById(R.id.edtPasse);
        btnLogin = findViewById(R.id.btnLogin);
        cbRemember = findViewById(R.id.cbRemember);
    }

    fun alerter(s: String) {
        Log.i(CAT, s)
        val t = Toast.makeText(this, s, Toast.LENGTH_SHORT)
        t.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        when (id) {
            R.id.action_settings -> alerter("Préférences")
            R.id.action_account -> alerter("Compte")
        }
        return super.onOptionsItemSelected(item)
    }
}