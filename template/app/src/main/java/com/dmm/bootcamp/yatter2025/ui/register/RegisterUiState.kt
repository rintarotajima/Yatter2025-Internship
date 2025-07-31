package com.dmm.bootcamp.yatter2025.ui.register

import com.dmm.bootcamp.yatter2025.ui.register.bindingmodel.RegisterBindingModel

data class RegisterUiState(
    val registerBindingModel: RegisterBindingModel,
    val isLoading: Boolean,
    // 新規登録画面で、UiStateにユーザー名やパスワードが適切に入力されているのかどうかというフラグを持たせて、
    // 新規登録処理実行可能な状態になってからログインボタンを押せるようにする。
    val validUsername: Boolean,
    val validPassword: Boolean,
) {
    val isEnableSignup: Boolean = validUsername && validPassword

    companion object Companion {
        fun empty(): RegisterUiState = RegisterUiState(
            registerBindingModel = RegisterBindingModel(
                username = "",
                password = "",
            ),
            isLoading = false,
            validUsername = false,
            validPassword = false,
        )
    }
}


