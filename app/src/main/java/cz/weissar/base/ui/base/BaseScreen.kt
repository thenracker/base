package cz.weissar.base.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cz.weissar.base.common.enums.Failure
import cz.weissar.base.common.enums.Loading
import cz.weissar.base.common.enums.State

@Composable
fun BaseStateScreen(
    state: State,
    showFullscreenLoading: Boolean = false,
    content: @Composable () -> Unit,
) {

    when (state) {
        is Loading -> {
            if (showFullscreenLoading) {
                LoadingIndicator()
            }

        }
        is Failure -> {
            ErrorDialog(
                failure = state,
                onDismiss = { /*vm.dismissErrorDialog()*/ },
            )
        }
        else -> { /* Success not important */}
    }

    content()
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Black)

    )
    {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ErrorDialog(
    failure: Failure,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        title = { Text(text = "Failure title") },
        text = { Text(failure.exception.message.toString()) },
        confirmButton = {},
        onDismissRequest = { },
        dismissButton = {
            Button(
                onClick = { onDismiss.invoke() }) {
                Text("Close")
            }
        }
    )
}