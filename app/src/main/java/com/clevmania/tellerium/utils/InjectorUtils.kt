package com.clevmania.tellerium.utils

import android.content.Context

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */

object InjectorUtils {
    fun getPreference(context: Context): PreferenceProvider{
        return PreferenceProvider(context)
    }
}
