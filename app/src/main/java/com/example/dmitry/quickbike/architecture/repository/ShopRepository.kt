package com.example.dmitry.quickbike.architecture.repository

import com.example.dmitry.quickbike.db.BikeDatabase

open class ShopRepository(private val mDataBase: BikeDatabase) {
    open fun readShops()  = mDataBase.shopsDao().getAllShops()
}