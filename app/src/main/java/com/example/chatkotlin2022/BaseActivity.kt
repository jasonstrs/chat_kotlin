package com.example.chatkotlin2022

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        when (id) {
            R.id.action_settings -> {
                // Changer d'activité pour afficher SettingsActivity
                // Changer d'activité pour afficher SettingsActivity
                val toSettings = Intent(this, SettingsActivity::class.java)
                startActivity(toSettings)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}