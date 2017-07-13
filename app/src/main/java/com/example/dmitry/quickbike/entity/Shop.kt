package com.example.dmitry.quickbike.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Shop(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var companyId: Long,
        //TODO LM_LM change to real type
        var location: String
)