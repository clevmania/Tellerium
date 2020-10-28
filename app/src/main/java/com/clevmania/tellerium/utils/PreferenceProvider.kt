package com.clevmania.tellerium.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.clevmania.tellerium.utils.Constants.PREF_DEFAULT_IMAGE_URL
import com.clevmania.tellerium.utils.Constants.PREF_IMAGE_URL_KEY
import javax.inject.Inject

/**
 * @author by Lawrence on 10/27/20.
 * for Tellerium
 */
class PreferenceProvider @Inject constructor(context: Context) {
    private val sharedPref =
        context.getSharedPreferences(Constants.PREF_IMAGE_URL, Context.MODE_PRIVATE)

    fun setImageBaseUrl(url: String) {
        sharedPref.edit(commit = true) { putString(PREF_IMAGE_URL_KEY, url) }
    }

    fun getImageBaseUrl(): String {
        return sharedPref.getString(PREF_IMAGE_URL_KEY, PREF_DEFAULT_IMAGE_URL)!!
    }
}