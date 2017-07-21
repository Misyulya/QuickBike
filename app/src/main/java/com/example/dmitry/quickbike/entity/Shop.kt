package com.example.dmitry.quickbike.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Entity
data class Shop(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0L,
        var companyId: Long,
        var name: String,
        var latitude: Double,
        var longitude: Double) {

    fun getMarkerOptions(): MarkerOptions {
        return MarkerOptions().position(LatLng(latitude, longitude)).title(name)
    }
}