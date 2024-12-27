package com.qti.splitbill.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.qti.splitbill.entity.DataLogin


object SessionManager {

    private const val KEY_DATA_USER = "user"
    private const val KEY_DEFAULT = "QTI"

    private fun getSharedPreference(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setLoginData(context: Context, dataLogin: DataLogin) {
        val editor = getSharedPreference(context).edit()
        val gson = Gson()
        editor.putString(KEY_DATA_USER, gson.toJson(dataLogin))
        editor.apply()
    }

    fun getLoginData(context: Context): DataLogin? {
        return try {
            val json = getSharedPreference(context).getString(KEY_DATA_USER, KEY_DEFAULT)
            if (json!!.length > 0) {
                Gson().fromJson(json, DataLogin::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            return null
        }
    }

    fun clearAllData(context: Context) {
        val editor = getSharedPreference(context).edit()
        editor.remove(KEY_DATA_USER)
        editor.apply()
    }
}