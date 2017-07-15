package com.example.dmitry.quickbike.di

import com.example.dmitry.quickbike.architecture.vm.MapViewModel
import com.example.dmitry.quickbike.db.BikeDatabase
import dagger.Component

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mapViewModel: MapViewModel)

    fun getDb() : BikeDatabase
}