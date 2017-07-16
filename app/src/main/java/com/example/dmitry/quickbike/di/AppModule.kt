package com.example.dmitry.quickbike.di

import android.app.Application
import android.arch.persistence.room.Room
import com.example.dmitry.quickbike.architecture.repository.ShopRepository
import com.example.dmitry.quickbike.db.BikeDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule (val app: Application){

    @Provides
    fun providesDb() : BikeDatabase {
        return Room.databaseBuilder(app, BikeDatabase::class.java, "SampleDb").build()
    }

    @Provides
    fun providesShopRepository(database: BikeDatabase) : ShopRepository {
        return ShopRepository(database)
    }
}