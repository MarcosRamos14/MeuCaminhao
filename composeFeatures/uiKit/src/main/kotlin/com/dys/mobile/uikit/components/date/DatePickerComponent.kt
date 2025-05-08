package com.dys.mobile.uikit.components.date

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextAndTimeComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.White
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent(
    modifier: Modifier = Modifier,
    onStartDateChange: (String) -> Unit,
    onEndDateChange: (String) -> Unit,
    hasDividerTop: Boolean = true,
    hasDividerBottom: Boolean = true
) {
    var openDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    var isStartDateSelection by remember { mutableStateOf(true) }

    val dateFormatter = remember {
        DateTimeFormatter.ofPattern("dd LLL, yyyy", Locale("pt", "BR"))
    }
    val todayFormatted = remember {
        LocalDate.now().format(dateFormatter)
    }

    var startDate by remember { mutableStateOf(todayFormatted) }
    var endDate by remember { mutableStateOf(todayFormatted) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
    ) {
        if (hasDividerTop) {
            HorizontalDivider(color = Gray80, thickness = 1.dp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12._dph),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4._dpw, Alignment.CenterHorizontally)
        ) {
            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(R.string.text_start_date_prefix),
                style = MaterialTheme.typography.bodySmall
            )

            TextAndTimeComponent(
                modifier = Modifier.clickable {
                    isStartDateSelection = true
                    openDatePicker = true
                },
                time = startDate
            )

            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(R.string.text_end_date_prefix),
                style = MaterialTheme.typography.bodySmall
            )

            TextAndTimeComponent(
                modifier = Modifier.clickable {
                    isStartDateSelection = false
                    openDatePicker = true
                },
                time = endDate
            )
        }

        if (hasDividerBottom) {
            HorizontalDivider(color = Gray80, thickness = 1.dp)
        }
    }

    AnimatedVisibility(openDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                openDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val selectedLocalDate = LocalDate.ofEpochDay(
                                millis / (24 * 60 * 60 * 1000)
                            )

                            if (isStartDateSelection) {
                                startDate = selectedLocalDate.format(dateFormatter)
                                onStartDateChange(startDate)

                                val endLocalDate = LocalDate.parse(endDate, dateFormatter)
                                if (selectedLocalDate.isAfter(endLocalDate)) {
                                    endDate = selectedLocalDate.format(dateFormatter)
                                }
                            } else {
                                val startLocalDate = LocalDate.parse(startDate, dateFormatter)
                                if (selectedLocalDate.isBefore(startLocalDate)) {
                                    //TODO: Show SnackBar (Implementação em outra branch)
                                } else {
                                    endDate = selectedLocalDate.format(dateFormatter)
                                    onEndDateChange(endDate)
                                }
                            }
                        }
                        openDatePicker = false
                    }
                ) {
                    TextComponent(
                        modifier = Modifier.wrapContentWidth(),
                        text = stringResource(R.string.common_text_ok),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { openDatePicker = false }
                ) {
                    TextComponent(
                        modifier = Modifier.wrapContentWidth(),
                        text = stringResource(R.string.common_text_cancel),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.onBackground,
            )
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                )
            )
        }
    }
}

@Preview
@Composable
private fun DatePickerPreview() {
    MeuCaminhaoTheme {
        DatePickerComponent(
            onStartDateChange = {},
            onEndDateChange = {}
        )
    }
}