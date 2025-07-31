package com.dmm.bootcamp.yatter2025.ui.timeline

import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

// UI全体(パブリックタイムライン画面)の状態を管理するための状態クラス
data class PublicTimelineUiState(
    val yweetList: List<YweetBindingModel>,
    val isLoading: Boolean,
    val isRefreshing: Boolean,
){
    // companion objectにすることでPublicUiStateクラスをインスタンスかしなくてもメソッドを呼び出すことができる
    companion object {
        fun empty(): PublicTimelineUiState = PublicTimelineUiState(
            yweetList = emptyList(),
            isLoading = false,
            isRefreshing = false,
        )
    }
}
