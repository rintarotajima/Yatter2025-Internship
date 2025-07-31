package com.dmm.bootcamp.yatter2025.ui.post.bindingmodel

// ツイート画面に表示するツイート者のアイコンとツイート内容を表現
data class PostBindingModel(
    val avatarUrl: String?,
    val yweetText: String,
)
