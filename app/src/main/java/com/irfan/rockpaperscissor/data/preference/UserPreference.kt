package com.irfan.rockpaperscissor.data.preference

import android.content.Context
import android.content.SharedPreferences

class UserPreference(context: Context) {
    private var preference: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    companion object {
        private const val NAME = "RockPaperScissor" //app name or else
        private const val MODE = Context.MODE_PRIVATE
        private val PREF_USER_NAME = Pair("USER_NAME", null)
    }

    var userName: String?
        get() = preference.getString(PREF_USER_NAME.first, PREF_USER_NAME.second)
        set(value) = preference.edit {
            it.putString(PREF_USER_NAME.first, value)
        }
}

private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}