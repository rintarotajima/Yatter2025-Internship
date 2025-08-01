package com.dmm.bootcamp.yatter2025.ui.register

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.dmm.bootcamp.yatter2025.common.navigation.Destination

class RegisterDestination(
    builder: (NavOptionsBuilder.() -> Unit)? = null,
) : Destination(ROUTE, builder) {
    companion object Companion {
        private const val ROUTE = "signup"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(ROUTE) {
                RegisterPage()
            }
        }
    }
}