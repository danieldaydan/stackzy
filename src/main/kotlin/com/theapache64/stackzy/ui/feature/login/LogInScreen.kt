package com.theapache64.stackzy.ui.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.theapache64.stackzy.ui.common.CustomScaffold
import com.theapache64.stackzy.ui.theme.R
import com.theapache64.stackzy.ui.util.Preview

fun main(args: Array<String>) {
    Preview {
        LogInScreen(
            viewModel = LogInScreenViewModel(),
            onLoggedIn = {

            },
            onBackClicked = {

            }
        )
    }
}

@Composable
fun LogInScreen(
    viewModel: LogInScreenViewModel,
    onLoggedIn: () -> Unit,
    onBackClicked: () -> Unit
) {

    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val isUsernameError by viewModel.isUsernameError.collectAsState()
    val isPasswordError by viewModel.isPasswordError.collectAsState()

    CustomScaffold(
        title = "Configure Profile",
        subTitle = "Login with your Google account",
        onBackClicked = onBackClicked
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .width(400.dp)
                    .padding(20.dp)
            ) {


                // Warning: to protect your privacy, do not use your primary account, but 'register a secondary one' exclusively for use with
                // Stackzy.
                Text(
                    text = getWarningText(),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.secondary,
                            RoundedCornerShape(10.dp)
                        ).padding(12.dp)
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                // Username
                OutlinedTextField(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = ""
                        )
                    },
                    singleLine = true,
                    value = username,
                    label = {
                        Text(
                            text = "Username",
                        )
                    },
                    onValueChange = { newUsername ->
                        viewModel.onNewUsername(newUsername)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isError = isUsernameError
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                // Password
                OutlinedTextField(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Password,
                            contentDescription = ""
                        )
                    },
                    singleLine = true,
                    value = password,
                    label = {
                        Text(
                            text = "Password",
                        )
                    },
                    onValueChange = { newPassword ->
                        viewModel.onNewPassword(newPassword)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    isError = isPasswordError
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                // Button
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.onLogInClicked()
                    }
                ) {
                    Text(text = "LOGIN")
                }
            }

        }
    }
}

@Composable
fun getWarningText(): AnnotatedString {
    return with(AnnotatedString.Builder("")) {

        // Red
        pushStyle(SpanStyle(color = R.color.YellowGreen))
        append("IMPORTANT: ")

        // Normal content color
        pushStyle(SpanStyle(color = MaterialTheme.colors.onSurface.copy(0.8f)))
        append("To protect your privacy, DO NOT use your primary account, but register a secondary one exclusively for Stackzy.")

        toAnnotatedString()
    }
}