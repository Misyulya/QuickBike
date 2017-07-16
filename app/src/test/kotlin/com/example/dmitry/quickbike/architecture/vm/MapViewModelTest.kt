package com.example.dmitry.quickbike.architecture.vm

import com.example.dmitry.quickbike.BaseAppUnitTest
import com.example.dmitry.quickbike.architecture.repository.ShopRepository
import com.example.dmitry.quickbike.entity.Shop
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals


class MapViewModelTest : BaseAppUnitTest() {
    @InjectMocks
    var mapsViewModel: MapViewModel = MapViewModel()

    @Mock lateinit var shopRepository : ShopRepository

    @Before
    fun onSetup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun init_ValidRepositoryResponse_ValidValueInLiveData() {
        // arrange
        val shopsStub = arrayListOf(Shop(companyId = 1L, latitude = 1.1, longitude = 1.1, name = "Stub shop"))
        `when`(shopRepository.readShops()).thenReturn(
                Flowable.just(shopsStub)
        )

        // act
        mapsViewModel.init()

        // assert
        assertEquals(shopsStub, mapsViewModel.init().shopsLiveData.value)
    }
}
