package com.example.dmitry.quickbike.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Company(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var name: String
)