package com.dys.mobile.uikit.components.stepper

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.theme.Gray65
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun StepperRouteComponent() {
    Column(
        modifier = Modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_circle),
            contentDescription = stringResource(R.string.text_start_date_prefix),
            tint = Color.Unspecified
        )
        
        Spacer(modifier = Modifier.height(2._dph))

        Canvas(
            modifier = Modifier
                .height(60._dph)
                .width(1._dpw)
        ) {
            val totalHeight = size.height
            var currentY = 0f
            while (currentY < totalHeight) {
                drawLine(
                    color = Gray65,
                    start = Offset(x = size.width / 2, y = currentY),
                    end = Offset(x = size.width / 2, y = currentY + 10f),
                    strokeWidth = size.width
                )
                currentY += 10f + 6f
            }
        }

        Spacer(modifier = Modifier.height(4._dph))

        Icon(
            painter = painterResource(R.drawable.ic_location_on),
            contentDescription = stringResource(R.string.text_to_prefix),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StepperRoutePreview() {
    MeuCaminhaoTheme {
        StepperRouteComponent()
    }
}