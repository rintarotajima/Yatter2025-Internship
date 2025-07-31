package com.dmm.bootcamp.yatter2025.ui.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import com.dmm.bootcamp.yatter2025.domain.repository.YweetRepository
import com.dmm.bootcamp.yatter2025.ui.post.PostDestination
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.converter.YweetConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PublicTimelineViewModel(
    private val yweetRepository: YweetRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<PublicTimelineUiState> =
        MutableStateFlow(PublicTimelineUiState.empty())
    val uiState: StateFlow<PublicTimelineUiState> = _uiState.asStateFlow()


    // 遷移先の情報としてDestination?のStateFlowを定義
    private val _destination = MutableStateFlow<Destination?>(null)
    val destination: StateFlow<Destination?> = _destination.asStateFlow()

    // YweetRepositoryからyweetListを取得して、_uiState(PublicTimelineUiState)を更新するメソッド
    private suspend fun fetchPublicTimeLine() {
        val yweetList = yweetRepository.findAllPublic()
        _uiState.update {
            it.copy(
                yweetList = YweetConverter.convertToBindingModel(yweetList)
            )
        }
    }

    // ViewModel外から呼び出されるメソッド
    // ViewModelから公開するメソッド名は、処理の内容ではなくUI側のイベントに合わせた名前にする

    fun onResume() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            fetchPublicTimeLine()
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            fetchPublicTimeLine()
            _uiState.update { it.copy(isRefreshing = false) }
        }
    }

    // FABが押されたことを処理するメソッド
    fun onClickPost() {
        _destination.value  = PostDestination()
    }

    // 遷移が完了したあとに_destinationをクリアするメソッド
    fun onCompleteNavigation() {
        _destination.value = null
    }
}