package com.dmm.bootcamp.yatter2025.ui.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmm.bootcamp.yatter2025.ui.theme.Yatter2025Theme

@Composable
fun  RegisterTemplate(
    userName: String,
    onChangedUserName: (String) -> Unit,
    password: String,
    onChangedPassword: (String) -> Unit,
    isEnableSignup: Boolean,
    isLoading: Boolean,
    onClickSignup: () -> Unit,
    onClickLogin: () -> Unit,
) {
    Scaffold(
      topBar = {
          TopAppBar(
              title = {
                  Text(text = "新規登録")
              }
          )
      }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = "ユーザー名"
                )
                OutlinedTextField(
                    value = userName,
                    onValueChange = onChangedUserName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    placeholder = {
                        Text(text = "username")
                    }
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "パスワード"
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = onChangedPassword,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    placeholder = {
                        Text(text = "password")
                    }
                )

                Button(
                    enabled = isEnableSignup,
                    onClick = onClickSignup,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(text = "新規登録")
                }

                Divider(modifier = Modifier.padding(vertical = 16.dp))

                TextButton(
                    onClick = onClickLogin,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "ログインはこちら")
                }


            }
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable fun SignupTemplatePreview() {
    Yatter2025Theme {
        Surface {
            RegisterTemplate(
                userName = "",
                onChangedUserName = {},
                password = "",
                onChangedPassword = {},
                isEnableSignup = false,
                isLoading = false,
                onClickSignup = {},
                onClickLogin = {},
            )
        }
    }
}