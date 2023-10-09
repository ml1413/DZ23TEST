package com.example.dz23test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dz23test.Response.Repository
import com.example.dz23test.model.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResponseModelViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>(Empty)
    val uiState: LiveData<UiState> = _uiState

    fun getCryptoByName(name: String) {
        _uiState.value = Processing
        viewModelScope.launch {
            var response: ResponseModel? = null
            response = repository.getCryptoByName(name = name)
            if (response.data != null) {
                _uiState.postValue(Success(model = response))
            } else {
                _uiState.postValue(Error("Error"))
            }

        }
    }


    sealed class UiState()
    data object Empty : UiState()
    data object Processing : UiState()
    class Success(val model: ResponseModel) : UiState()
    class Error(val error: String) : UiState()
}