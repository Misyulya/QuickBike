package com.example.dmitry.quickbike.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.dmitry.quickbike.entity.Bicycle
import io.reactivex.Flowable

@Dao
interface BikesDao {
    @Query("SELECT * FROM Bicycle")
    fun getAllBikes() : Flowable<List<Bicycle>>

    @Insert fun insertMultiple(shop: List<Bicycle>)
}