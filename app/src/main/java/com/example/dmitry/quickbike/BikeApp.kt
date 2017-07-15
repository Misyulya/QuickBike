package com.example.dmitry.quickbike

import android.app.Application
import com.example.dmitry.quickbike.di.AppComponent
import com.example.dmitry.quickbike.di.AppModule
import com.example.dmitry.quickbike.di.DaggerAppComponent
import com.example.dmitry.quickbike.utils.fillContent
import com.example.dmitry.quickbike.utils.isEmpty

class BikeApp : Application() {
    companion object {
        lateinit var instance: BikeApp
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = createComponent()
        val db = appComponent.getDb()
        db.isEmpty()
                .filter { isEmpty -> isEmpty }
                .flatMapCompletable { db.fillContent() }
                .subscribe()

    }

    private fun createComponent(): AppComponent {
        return DaggerAppComponent.
                builder()
                .appModule(AppModule(this))
                .build()
    }
}