package com.dmm.bootcamp.yatter2025.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import com.dmm.bootcamp.yatter2025.domain.model.Password
import com.dmm.bootcamp.yatter2025.domain.model.Username
import com.dmm.bootcamp.yatter2025.ui.timeline.PublicTimelineDestination
import com.dmm.bootcamp.yatter2025.usecase.login.LoginUseCase
import com.dmm.bootcamp.yatter2025.usecase.login.LoginUseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.empty())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _destination = MutableStateFlow<Destination?>(null)
    val destination: StateFlow<Destination?> = _destination.asStateFlow()


    fun onChangedUsername(username: String) {
        val snapshotBindingLoginViewModel = uiState.value.loginBindingModel
        _uiState.update {
            it.copy(
                validUsername = Username(username).validate(),
                loginBindingModel = snapshotBindingLoginViewModel.copy(
                    username = username
                )
            )
        }
    }

    fun onCHangedPassword(password: String) {
        val snapshotBindingModel = uiState.value.loginBindingModel
        _uiState.update {
            it.copy(
                validPassword = Username(password).validate(),
                loginBindingModel = snapshotBindingModel.copy(
                    password = password
                )
            )
        }
    }

    fun onClickLogin() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val snapBindingModel = uiState.value.loginBindingModel
            when (
                val result =
                    loginUseCase.execute(
                        Username(snapBindingModel.username),
                        Password(snapBindingModel.password),
                    )
            ) {
                is LoginUseCaseResult.Success -> {
                    _destination.value = PublicTimelineDestination()
                }
                is LoginUseCaseResult.Failure -> {

                }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onClickRegister() {
//        _destination.value = RegisterUserDestination()
    }

    fun onCompleteNavigation() {
        _destination.value  = null
    }
}