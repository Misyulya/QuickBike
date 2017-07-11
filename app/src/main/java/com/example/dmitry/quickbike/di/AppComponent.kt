package com.example.dmitry.quickbike.di

import com.example.dmitry.quickbike.activity.TestActivity
import dagger.Component

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(testActivity: TestActivity)
}