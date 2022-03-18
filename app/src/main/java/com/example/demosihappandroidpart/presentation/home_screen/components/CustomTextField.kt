package com.example.demosihappandroidpart.presentation.home_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.demosihappandroidpart.presentation.text_field_state.TextFieldState

@Composable
fun CustomTextField(
    textFieldState: TextFieldState,
    modifier: Modifier,
    placeholder: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = textFieldState.text,
        onValueChange = onValueChange,
        isError = textFieldState.error.isNotBlank(),
        label = {
            Text(text = textFieldState.label)
        },
        placeholder = {
            Text(if(textFieldState.error.isNotBlank()) placeholder else "")
        },
        modifier = modifier,
        keyboardOptions = keyboardOptions
    )
}