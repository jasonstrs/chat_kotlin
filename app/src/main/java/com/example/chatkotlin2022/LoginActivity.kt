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
    var gs: GlobalState? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtLogin = findViewById(R.id.edtLogin)
        edtPasse = findViewById(R.id.edtPasse)
        btnLogin = findViewById(R.id.btnLogin)
        cbRemember = findViewById(R.id.cbRemember)
        gs = getApplication() as GlobalState
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        when (id) {
            R.id.action_settings -> gs?.alerter("Préférences")
            R.id.action_account -> gs?.alerter("Compte")
        }
        return super.onOptionsItemSelected(item)
    }
}