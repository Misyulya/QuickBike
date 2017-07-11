package com.example.dmitry.quickbike

import android.app.Application
import com.example.dmitry.quickbike.di.AppComponent
import com.example.dmitry.quickbike.di.AppModule
import com.example.dmitry.quickbike.di.DaggerAppComponent

class BikeApp : Application() {
    companion object {
        lateinit var instance : BikeApp
        lateinit var  appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = createComponent()
    }

    private fun createComponent(): AppComponent {
        return DaggerAppComponent.
                builder()
                .appModule(AppModule(this))
                .build()
    }
}