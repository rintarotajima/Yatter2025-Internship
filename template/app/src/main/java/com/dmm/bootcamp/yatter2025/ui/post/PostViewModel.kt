package com.dmm.bootcamp.yatter2025.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import com.dmm.bootcamp.yatter2025.common.navigation.PopBackDestination
import com.dmm.bootcamp.yatter2025.domain.service.GetLoginUserService
import com.dmm.bootcamp.yatter2025.usecase.post.PostYweetUseCase
import com.dmm.bootcamp.yatter2025.usecase.post.PostYweetUseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel(
    private val postYweetUseCase: PostYweetUseCase,
    private val getLoginUserService: GetLoginUserService,
) : ViewModel() {
    // ViewModel内のUiState
    private val _uiState: MutableStateFlow<PostUiState> = MutableStateFlow(PostUiState.empty())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    // 画面遷移のためのStateFlow
    private val _destination = MutableStateFlow<Destination?>(null)
    val destination: StateFlow<Destination?> = _destination.asStateFlow()

    // 画面の初期起動時にユーザー情報を取得するメソッド
    fun onCreate() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val me = getLoginUserService.execute()

            val snapshotBindingModel = uiState.value.bindingModel
            _uiState.update {
                it.copy(
                    bindingModel = snapshotBindingModel.copy(avatarUrl = me?.avatar?.toString()),
                    isLoading = false,
                )
            }
        }
    }

    // Yweetの内容を変更した時に呼び出されるメソッド
    fun onChangedYweetText(yweetText: String) {
        _uiState.update { it.copy(bindingModel = uiState.value.bindingModel.copy(yweetText = yweetText)) }
    }

    // 投稿ボタンを押した時に呼び出されるメソッド
    fun onClickPost() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = postYweetUseCase.execute(
                content = uiState.value.bindingModel.yweetText,
                attachmentList = listOf(),
            )
            when (result) {
                PostYweetUseCaseResult.Success -> {
                    _destination.value = PopBackDestination
                }
                is PostYweetUseCaseResult.Failure -> {

                }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    // パブリックタイムライン画面に戻るために表示するボタン押下時のメソッド
    fun onClickNavIcon() {
        _destination.value = PopBackDestination
    }

    // 画面遷移が終わった後にDestinationを初期化するメソッド
    fun onCompleteNavigation() {}
}