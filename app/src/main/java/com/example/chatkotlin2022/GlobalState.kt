package com.example.chatkotlin2022

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class GlobalState : Application() {
    val CAT = "IG2I"

    fun alerter(s: String) {
        Log.i(CAT, s)
        val t = Toast.makeText(this, s, Toast.LENGTH_SHORT)
        t.show()
    }

    @kotlin.jvm.Throws(IOException::class)
    private fun convertStreamToString(`in`: InputStream?): String {
        return try {
            val reader = BufferedReader(InputStreamReader(`in`))
            val sb = StringBuilder()
            var line: String? = null
            while (reader.readLine().also({ line = it }) != null) {
                sb.append("""
    ${line.toString()}
    
    """.trimIndent())
            }
            sb.toString()
        } finally {
            try {
                `in`?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    
    fun verifReseau(): Boolean {
        // On vérifie si le réseau est disponible,
        // si oui on change le statut du bouton de connexion
        val cnMngr: ConnectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo: NetworkInfo? = cnMngr.getActiveNetworkInfo()
        var sType = "Aucun réseau détecté"
        var bStatut = false
        if (netInfo != null) {
            val netState: NetworkInfo.State = netInfo.getState()
            if (netState.compareTo(NetworkInfo.State.CONNECTED) === 0) {
                bStatut = true
                val netType: Int = netInfo.getType()
                when (netType) {
                    ConnectivityManager.TYPE_MOBILE -> sType = "Réseau mobile détecté"
                    ConnectivityManager.TYPE_WIFI -> sType = "Réseau wifi détecté"
                }
            }
        }
        alerter(sType)
        return bStatut
    }

    fun requeteGET(urlData: String?, qs: String?): String? {
        if (qs != null) {
            try {
                val url = URL("$urlData?$qs")
                Log.i(CAT, "url utilisée : " + url.toString())
                var urlConnection: HttpURLConnection? = null
                urlConnection = url.openConnection() as HttpURLConnection
                var `in`: InputStream? = null
                `in` = BufferedInputStream(urlConnection.getInputStream())
                val txtReponse = convertStreamToString(`in`)
                urlConnection.disconnect()
                return txtReponse
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return ""
    }

    fun requetePOST(urlData: String?, qs: String?): String? {
        var dataout: DataOutputStream? = null // new:POST
        if (qs != null) {
            try {
                val url = URL(urlData) // new:POST
                Log.i(CAT, "url utilisée : " + url.toString())
                var urlConnection: HttpURLConnection? = null
                urlConnection = url.openConnection() as HttpURLConnection

                // new:POST
                urlConnection.setRequestMethod("POST")
                urlConnection.setDoOutput(true)
                urlConnection.setDoInput(true)
                urlConnection.setUseCaches(false)
                urlConnection.setAllowUserInteraction(false)
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                dataout = DataOutputStream(urlConnection.getOutputStream())
                dataout.writeBytes(qs)
                // new:POST
                var `in`: InputStream? = null
                `in` = BufferedInputStream(urlConnection.getInputStream())
                val txtReponse = convertStreamToString(`in`)
                urlConnection.disconnect()
                return txtReponse
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return ""
    }
}