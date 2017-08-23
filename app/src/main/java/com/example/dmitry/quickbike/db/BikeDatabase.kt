package com.example.dmitry.quickbike.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.dmitry.quickbike.db.dao.BikesDao
import com.example.dmitry.quickbike.db.dao.ShopsDao
import com.example.dmitry.quickbike.entity.Bicycle
import com.example.dmitry.quickbike.entity.Company
import com.example.dmitry.quickbike.entity.Shop

@Database(entities = arrayOf(Bicycle::class, Shop::class, Company::class), version = 1)
abstract class BikeDatabase : RoomDatabase(){
    abstract fun shopsDao() : ShopsDao
    abstract fun bikesDao() : BikesDao
}