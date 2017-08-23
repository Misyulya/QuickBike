package com.example.dmitry.quickbike.architecture.vm

import android.arch.lifecycle.MutableLiveData
import com.example.dmitry.quickbike.BikeApp
import com.example.dmitry.quickbike.entity.ShopWithBicycles
import com.example.dmitry.quickbike.architecture.repository.ShopRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MapViewModel : BaseViewModel() {

    val shopsLiveData: MutableLiveData<List<ShopWithBicycles>> by lazy {
        MutableLiveData<List<ShopWithBicycles>>()
    }
    private var mShopsDisposable: Disposable? = null

    @Inject lateinit internal var mShopRepository: ShopRepository

    init {
        BikeApp.appComponent.inject(this)
    }

    fun init(): MapViewModel {
        if (mShopsDisposable == null) {
            loadShops()
        }
        return this
    }

    private fun loadShops() {

        mShopsDisposable = mShopRepository.readShops().nonBlockingUi().subscribe(
                { requireNotNull(shopsLiveData).setValue(it) },
                { throw it }
        )
    }
}
