package com.theapache64.stackzy.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.theapache64.stackzy.util.R

/**
 * To show a basic content page with title
 */
@Composable
fun ContentScreen(
    title: String,
    modifier: Modifier = Modifier,
    onBackClicked: (() -> Unit)? = null,
    topRightSlot: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (onBackClicked != null) {
                IconButton(
                    onClick = onBackClicked,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ChevronLeft,
                        contentDescription = R.string.select_app_cd_go_back
                    )
                }
            }

            Text(
                text = title,
                style = MaterialTheme.typography.h3
            )

            topRightSlot?.invoke()
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        content()
    }

}