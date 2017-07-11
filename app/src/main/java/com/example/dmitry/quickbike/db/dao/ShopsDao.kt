package com.example.dmitry.quickbike.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.dmitry.quickbike.entity.Shop
import io.reactivex.Flowable

@Dao
interface ShopsDao {
    @Query("SELECT * FROM Shop")
    fun getAllShops() : Flowable<List<Shop>>

    @Insert fun insert(shop: Shop)
}