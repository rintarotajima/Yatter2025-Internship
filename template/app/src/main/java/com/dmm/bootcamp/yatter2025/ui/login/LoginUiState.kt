package com.dmm.bootcamp.yatter2025.ui.login

import com.dmm.bootcamp.yatter2025.ui.login.bindingModel.LoginBindingModel

data class LoginUiState(
    val loginBindingModel: LoginBindingModel,
    val isLoading: Boolean,
    val validUsername: Boolean,
    val validPassword: Boolean,
) {

    val isEnableLogin: Boolean = validUsername && validPassword

    companion object {
        fun empty(): LoginUiState = LoginUiState(
            loginBindingModel = LoginBindingModel(
                username = "",
                password = "",
            ),
            isLoading = false,
            validUsername = false,
            validPassword = false,
        )
    }
}
