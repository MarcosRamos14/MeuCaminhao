package com.dys.mobile.toolkit.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation : VisualTransformation {

    /**
     * @return mask (00) 00000-0000
     */
    override fun filter(text: AnnotatedString): TransformedText {
        val phoneMask = text.text.mapIndexed { index, character ->
            when (index) {
                0 -> "($character"
                1 -> "$character) "
                6 -> "$character-"
                else -> character
            }
        }.joinToString(separator = "")

        return TransformedText(
            AnnotatedString(phoneMask),
            PhoneOffsetMapper
        )
    }

    object PhoneOffsetMapper : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when {
                (offset > 6) -> offset + 4
                (offset > 1) -> offset + 3
                (offset > 0) -> offset + 1
                else -> offset
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when {
                (offset >= 11) -> offset - 4
                (offset >= 5) -> offset - 3
                (offset == 4) -> offset - 2
                (offset >= 2) -> offset - 1
                else -> offset
            }
        }
    }
}