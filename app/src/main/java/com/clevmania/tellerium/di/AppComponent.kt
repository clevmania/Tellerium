package com.clevmania.tellerium.di

import android.content.Context
import com.clevmania.tellerium.MainActivity
import com.clevmania.tellerium.ui.farmer.FarmerFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @author by Lawrence on 10/28/20.
 * for Tellerium
 */
@Singleton
@Component(modules = [ServiceModule::class, DataBaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: FarmerFragment)

}