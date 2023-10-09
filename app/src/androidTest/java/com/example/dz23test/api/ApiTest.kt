package com.example.dz23test

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ApiTest : TestCase() {

    @Test
    fun test_Adi_Result_Success() {
        val api = TestApiImpl.provideApi()
        val test = runBlocking {
            api.getCryptoByName("bitcoin")
        }
        assertEquals(test.isSuccessful, true)
    }
}