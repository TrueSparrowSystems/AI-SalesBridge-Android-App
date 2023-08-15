package com.truesparrowsystemspvtltd.salessparrow.common_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.truesparrowsystemspvtltd.salessparrow.R


enum class ToastState(val value: String) {
    SUCCESS("success"),
    ERROR("error"),
    WARNING("warning"),
    INFO("info")
}


@Composable
fun CustomToast(
    toastState: ToastState,
    message: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .height(93.dp)
            .background(color = Color(0xFF63E17D))
            .padding(start = 24.dp, top = 10.dp, end = 24.dp, bottom = 10.dp)
            .zIndex(Float.MAX_VALUE)
    ) {
        when (toastState) {
            ToastState.SUCCESS -> {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.success_toast_check),
                        contentDescription = "Success",
                        modifier = Modifier
                            .height(24.dp)
                            .width(28.dp)
                    )
                    Text(
                        text = message,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }

            ToastState.ERROR -> {
                // Compose elements for error state
            }

            ToastState.WARNING -> {
                // Compose elements for warning state
            }

            ToastState.INFO -> {
                // Compose elements for info state
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomToastPreview() {
    CustomToast(
        toastState = ToastState.SUCCESS,
        message = "Note is saved to your Salesforce Account"
    )
}