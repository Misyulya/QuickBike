package com.example.dmitry.quickbike.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Bicycle (
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var shopId: Long,
        var price: Long,
        var name: String
        //TODO LM_LM add other attrs
)