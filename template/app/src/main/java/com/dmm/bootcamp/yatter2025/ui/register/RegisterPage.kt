package com.dmm.bootcamp.yatter2025.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dmm.bootcamp.yatter2025.ui.LocalNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun RegisterPage(
    registerViewModel: RegisterViewModel = getViewModel()
) {
    val uiState: RegisterUiState by registerViewModel.uiState.collectAsStateWithLifecycle()
    val destination by registerViewModel.destination.collectAsStateWithLifecycle()
    val navController = LocalNavController.current
    LaunchedEffect(destination) {
        destination?.let {
            it.navigate(navController)
            registerViewModel.onCompleteNavigation()
        }
    }
    RegisterTemplate(
        userName = uiState.registerBindingModel.username,
        onChangedUserName = registerViewModel::onChangedUsername,
        password = uiState.registerBindingModel.password,
        onChangedPassword = registerViewModel::onChangedPassword,
        isEnableSignup = uiState.isEnableSignup,
        isLoading = uiState.isLoading,
        onClickSignup = registerViewModel::onClickSignup,
        onClickLogin = registerViewModel::onClickLogin
    )
}