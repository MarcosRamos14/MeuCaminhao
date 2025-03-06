package com.dys.mobile.uikit.components.stepper

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.Gray70
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.White

@Composable
fun StepperComponent(
    currentStep: Int,
    textSteps: List<String>
) {
    ConstraintLayout(
        constraintSet = stepperConstraints(textSteps.size),
        modifier = Modifier.fillMaxWidth()
    ) {
        for (step in 1..textSteps.size) {
            val isCurrent = step == currentStep
            val isCompleted = step < currentStep
            val colorCircle = if (isCompleted) {
                MaterialTheme.colorScheme.primary
            } else {
                White
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(32._dpw)
                    .background(
                        color = colorCircle,
                        shape = CircleShape
                    )
                    .border(
                        width = if (isCurrent) 2._dpw else 0._dpw,
                        color = if (isCurrent) MaterialTheme.colorScheme.primary else Color.Transparent,
                        shape = CircleShape
                    )
                    .layoutId("circle_$step")

            ) {
                if (isCompleted) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                } else {
                    TextComponent(
                        text = step.toString(),
                        color = if (isCurrent) MaterialTheme.colorScheme.primary else Gray70,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            TextComponent(
                modifier = Modifier
                    .wrapContentWidth()
                    .layoutId("label_$step"),
                text = textSteps.getOrNull(step - 1) ?: "",
                color = if (isCurrent) MaterialTheme.colorScheme.primary else Black,
                fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            if (step < textSteps.size) {
                HorizontalDivider(
                    modifier = Modifier.layoutId("line_$step"),
                    thickness = 6._dph,
                    color = if (step < currentStep) MaterialTheme.colorScheme.primary else Gray70,
                )
            }
        }
    }
}

private fun stepperConstraints(totalSteps: Int): ConstraintSet {
    return ConstraintSet {
        val circles = (1..totalSteps).map { createRefFor("circle_$it") }
        val labels = (1..totalSteps).map { createRefFor("label_$it") }
        val lines = (1 until totalSteps).map { createRefFor("line_$it") }

        circles.forEachIndexed { index, circle ->
            constrain(circle) {
                top.linkTo(parent.top)

                when (index) {
                    0 -> {
                        start.linkTo(labels[index].start)
                        end.linkTo(labels[index].end)
                    }
                    totalSteps - 1 -> {
                        start.linkTo(labels[index].start)
                        end.linkTo(labels[index].end)
                    }
                    else -> {
                        start.linkTo(lines[index - 1].end)
                        end.linkTo(lines[index].start)
                    }
                }
            }

            constrain(labels[index]) {
                top.linkTo(circle.bottom, margin = 8._dpw)
                when (index) {
                    0 -> start.linkTo(parent.start)
                    totalSteps - 1 -> end.linkTo(parent.end)
                    else -> {
                        start.linkTo(circle.start)
                        end.linkTo(circle.end)
                    }
                }
            }
        }

        lines.forEachIndexed { index, line ->
            constrain(line) {
                top.linkTo(circles[index].top)
                bottom.linkTo(circles[index].bottom)
                start.linkTo(circles[index].end)
                end.linkTo(circles[index + 1].start)
                width = Dimension.fillToConstraints
            }
        }
    }
}

@Preview
@Composable
private fun StepperComponentPreview() {
    MeuCaminhaoTheme {
        StepperComponent(
            currentStep = 2,
            textSteps = listOf(
                stringResource(R.string.text_step_create_account),
                stringResource(R.string.text_step_account_type),
                stringResource(R.string.text_step_start_using)
            )
        )
    }
}