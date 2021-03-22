package com.sibela.stateflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.EmptyState)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun login(username: String, password: String) = viewModelScope.launch {
        _loginUiState.value = LoginUiState.Loading
        delay(2_000)
        if (username == "leonardo" && password == "123456") {
            _loginUiState.value = LoginUiState.Success
        } else {
            _loginUiState.value = LoginUiState.Error("Invalid user or password")
        }
    }

    sealed class LoginUiState {
        object EmptyState : LoginUiState()
        object Loading : LoginUiState()
        object Success : LoginUiState()
        data class Error(val message: String) : LoginUiState()
    }

}