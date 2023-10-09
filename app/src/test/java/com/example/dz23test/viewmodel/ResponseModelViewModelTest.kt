package com.example.dz23test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dz23test.Response.Repository
import com.example.dz23test.model.Data
import com.example.dz23test.model.ResponseModel
import com.example.dz23test.viewModel.ResponseModelViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class ResponseModelViewModelTest {
    private val repository: Repository = Mockito.mock(Repository::class.java)

    @Rule
    fun getRule() = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher(TestCoroutineScheduler()))
    }

    @Test
    fun `test view model state Empty`() {
        val viewModel = ResponseModelViewModel(repository = repository)
        Assert.assertEquals(viewModel.uiState.value, ResponseModelViewModel.Empty)
    }

    @Test
    fun `test view model state Processing`() {
        val viewModel = ResponseModelViewModel(repository = repository)
        viewModel.getCryptoByName("")
        Assert.assertEquals(viewModel.uiState.value, ResponseModelViewModel.Processing)
    }

    @Test
    fun `test view model state Success`() {
        val viewModel = ResponseModelViewModel(repository = repository)
        val successResponse = ResponseModel(data = Data(id = "1", symbol = "", rateUsd = ""))
        runBlocking {
            Mockito.`when`(repository.getCryptoByName("bitcoin")).thenReturn(successResponse)
        }
        viewModel.getCryptoByName("bitcoin")
        val model = viewModel.uiState.value as ResponseModelViewModel.Success
        Assert.assertEquals(model.model.data?.id, successResponse.data?.id)
    }

    @Test
    fun `test view model state Error`() {
        val viewModel = ResponseModelViewModel(repository = repository)
        val successResponse = ResponseModel(data = null)
        runBlocking {
            Mockito.`when`(repository.getCryptoByName("wrong name")).thenReturn(successResponse)
        }
        viewModel.getCryptoByName("wrong name")
        val model = viewModel.uiState.value as ResponseModelViewModel.Error
       Assert.assertEquals(model.error,"Error")
    }

}