package com.example.dmitry.quickbike.utils

import android.util.Log
import com.example.dmitry.quickbike.db.BikeDatabase
import com.example.dmitry.quickbike.entity.Shop
import io.reactivex.Completable
import io.reactivex.Observable
import sport.dev.sturdy.extension.doAsync

fun BikeDatabase.isEmpty(): Observable<Boolean> {
    return shopsDao().getShopsCount()
            .doAsync()
            .map { it == 0 }
            .doOnNext { Log.d("LmTest", "isEmpty onNext " + it) }
            .toObservable()
}

fun BikeDatabase.fillContent(): Completable {
    val shopsList: List<Shop> = arrayListOf(
            Shop(companyId = 1L, name = "Brest shop", latitude = 52.09755, longitude = 23.68775),
            Shop(companyId = 1L, name = "Gomel shop", latitude = 52.4345, longitude = 30.9754),
            Shop(companyId = 1L, name = "Mogilev shop", latitude = 53.900716, longitude = 30.33136),
            Shop(companyId = 1L, name = "Minsk shop", latitude = 53.900, longitude = 27.567)
    )

    return Completable.fromCallable {
        shopsDao().insertMultiple(shopsList)
    }.doAsync()
}

