package com.example.dmitry.quickbike.architecture.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Flowable
import sport.dev.sturdy.extension.doAsync
open class BaseViewModel : ViewModel() {
    var isUpdatingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun <T> Flowable<T>.nonBlockingUi(): Flowable<T> {
        isUpdatingLiveData.value = true
        return doAsync()
                //TODO LM_LM temp hack because room db doesn't send onComplete event
                .doOnNext {
                    isUpdatingLiveData.value = false
                }
                .doOnError { isUpdatingLiveData.value = false }
    }
}