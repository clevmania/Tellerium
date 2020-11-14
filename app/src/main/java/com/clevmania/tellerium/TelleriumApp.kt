package com.clevmania.tellerium

import android.app.Application
import com.clevmania.tellerium.di.AppComponent
import com.clevmania.tellerium.di.DaggerAppComponent

/**
 * @author by Lawrence on 10/28/20.
 * for Tellerium
 */
open class TelleriumApp : Application() {
    val appComponent : AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}