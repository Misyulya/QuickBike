package com.example.dmitry.quickbike.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.dmitry.quickbike.BikeApp
import com.example.dmitry.quickbike.db.BikeDatabase
import com.example.dmitry.quickbike.entity.Shop
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

class TestActivity : AppCompatActivity() {
    @Inject lateinit var mBikeDatabase: BikeDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BikeApp.appComponent.inject(this)
        val insertSource = Flowable.fromCallable {
            mBikeDatabase.shopsDao()
                    .insert(Shop(0L, 1L, "tempLocation"))
            true
        }


        insertSource.flatMap<List<Shop>> { it -> mBikeDatabase.shopsDao().getAllShops() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<Shop>> {
                    override fun onSubscribe(s: Subscription?) {
                    }

                    override fun onNext(companies: List<Shop>) {
                        Log.d("LmTest", "onNext " + companies.size)
                    }

                    override fun onError(t: Throwable) {
                        throw RuntimeException(t)
                    }

                    override fun onComplete() {
                        Log.d("LmTest", "onComplete")
                    }
                })
    }
}