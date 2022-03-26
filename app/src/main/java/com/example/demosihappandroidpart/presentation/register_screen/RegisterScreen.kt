package com.example.demosihappandroidpart.presentation.register_screen

import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.demosihappandroidpart.presentation.register_screen.components.CustomTextField
import com.example.demosihappandroidpart.presentation.text_field_state.TextFieldState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterScreenViewModel = hiltViewModel(),
    getContent: ActivityResultLauncher<String>
) {
    val firstNameState by viewModel.firstNameTextField.observeAsState(TextFieldState())
    val lastNameState by viewModel.lastNameTextField.observeAsState(TextFieldState())
    val ageState by viewModel.ageTextField.observeAsState(TextFieldState())
    val midState by viewModel.midTextField.observeAsState(TextFieldState())
    val houseNumberState by viewModel.houseNumberTextField.observeAsState(TextFieldState())
    val streetState by viewModel.streetTextField.observeAsState(TextFieldState())
    val villageState by viewModel.villageTextField.observeAsState(TextFieldState())

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        val context = LocalContext.current

        Column(modifier = modifier) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                CustomTextField(
                    textFieldState = firstNameState,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = 4.dp),
                    placeholder = "First name cannot be empty!",
                    onValueChange = viewModel::onFirstNameTextFieldChange,
                )
                CustomTextField(
                    textFieldState = lastNameState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    placeholder = "Last Name cannot be empty",
                    onValueChange = viewModel::onLastNameTextFieldChange,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                CustomTextField(
                    textFieldState = ageState,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = 4.dp),
                    placeholder = "Age cannot be empty",
                    onValueChange = viewModel::onAgeTextFieldChange,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                CustomTextField(
                    textFieldState = midState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    placeholder = "Worker Id cannot be empty",
                    onValueChange = viewModel::onMidTextFieldChange
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                CustomTextField(
                    textFieldState = houseNumberState,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = 4.dp),
                    placeholder = "House number cannot be empty",
                    onValueChange = viewModel::onHouseNumberTextFieldChange,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                CustomTextField(
                    textFieldState = streetState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    placeholder = "Street cannot be empty",
                    onValueChange = viewModel::onStreetTextFieldChange,
                )
            }
            CustomTextField(
                textFieldState = villageState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = "Village cannot be empty",
                onValueChange = viewModel::onVillageTextFieldChange,
            )

            Button(onClick = {getContent.launch("image/*")}, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Text(text = "Add Image")
            }

            Button(
                onClick = viewModel::onRegisterButtonClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Register")
            }

        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.event.receiveAsFlow().collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> scaffoldState.snackbarHostState.showSnackbar(message = event.message)
            }
        }
    }
}

