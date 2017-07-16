package com.example.dmitry.quickbike

import helper.TrampolineSchedulerRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = BikeApp::class, sdk = intArrayOf(18))
abstract class BaseAppUnitTest {
    //LM_ALL JvmField need because Junit doesn't see Rule otherwise
    @Rule @JvmField
    val mTrampolineSchedulerRule = TrampolineSchedulerRule()
}