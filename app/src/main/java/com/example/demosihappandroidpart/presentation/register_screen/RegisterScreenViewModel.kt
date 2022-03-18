package com.example.demosihappandroidpart.presentation.register_screen

import android.app.Application
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demosihappandroidpart.core.Constants
import com.example.demosihappandroidpart.domain.repository.SihRepo
import com.example.demosihappandroidpart.domain.use_cases.use_cases_get_all_workers.UseCaseGetAllWorkers
import com.example.demosihappandroidpart.presentation.text_field_state.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val getAllWorkersUseCase: UseCaseGetAllWorkers,
    private val repo: SihRepo,
    private val myApplication: Application
) : ViewModel() {

    val event: Channel<UiEvent> = Channel<UiEvent>()

    private val firstNameTextFieldLiveData: MutableLiveData<TextFieldState> =
        MutableLiveData(TextFieldState(label = "First Name"))
    val firstNameTextField: LiveData<TextFieldState> = firstNameTextFieldLiveData

    private val lastNameTextFieldLiveData: MutableLiveData<TextFieldState> =
        MutableLiveData(TextFieldState(label = "Last Name"))
    val lastNameTextField = lastNameTextFieldLiveData

    private val ageTextFieldLiveData: MutableLiveData<TextFieldState> =
        MutableLiveData(TextFieldState(label = "Age"))
    val ageTextField = ageTextFieldLiveData

    private val midTextFieldLiveData: MutableLiveData<TextFieldState> =
        MutableLiveData(TextFieldState(label = "Worker Id"))
    val midTextField = midTextFieldLiveData

    private val houseNumberTextFieldLiveData: MutableLiveData<TextFieldState> =
        MutableLiveData(TextFieldState(label = "House number"))
    val houseNumberTextField = houseNumberTextFieldLiveData

    private val streetTextFieldLiveData: MutableLiveData<TextFieldState> =
        MutableLiveData(TextFieldState(label = "Street"))
    val streetTextField = streetTextFieldLiveData

    private val villageTextFieldLiveData: MutableLiveData<TextFieldState> =
        MutableLiveData(TextFieldState(label = "Village"))
    val villageTextField = villageTextFieldLiveData

    init {
        viewModelScope.launch {
            getAllWorkers()
        }
    }

    fun onHouseNumberTextFieldChange(newValue: String) {
        houseNumberTextFieldLiveData.value = TextFieldState(
            text = newValue,
            error = if (newValue.isBlank()) "house number cannot be empty" else "",
            label = "House Number"
        )
    }

    fun onStreetTextFieldChange(newValue: String) {
        streetTextFieldLiveData.value = TextFieldState(
            text = newValue,
            error = if (newValue.isBlank()) "street cannot be empty" else "",
            label = "Street"
        )
    }

    fun onVillageTextFieldChange(newValue: String) {
        villageTextFieldLiveData.value = TextFieldState(
            text = newValue,
            error = if (newValue.isBlank()) "village cannot be empty" else "",
            label = "Village"
        )
    }

    fun onFirstNameTextFieldChange(value: String) {
        firstNameTextFieldLiveData.value = TextFieldState(
            text = value,
            error = if (value.isBlank()) "First Name cannot be empty" else "",
            label = "Last Name"
        )
    }

    fun onLastNameTextFieldChange(newValue: String) {
        lastNameTextFieldLiveData.value = TextFieldState(
            text = newValue,
            error = if (newValue.isBlank()) "Last Name cannot be empty" else "",
            label = "Last Name"
        )
    }

    fun onAgeTextFieldChange(newValue: String) {
        ageTextFieldLiveData.value = TextFieldState(
            text = newValue,
            error = if (newValue.isBlank()) "Age cannot be blank" else "",
            label = "Age"
        )
    }

    fun onMidTextFieldChange(newValue: String) {
        midTextFieldLiveData.value = TextFieldState(
            text = newValue,
            error = if (newValue.isBlank()) "Worker Id cannot be blank" else "",
            label = "Worker Id"
        )
    }

    fun onRegisterButtonClicked() {
        val stream = myApplication.resources.assets.open("eminem.jpg")
        val image = BitmapFactory.decodeStream(stream)
        viewModelScope.launch (IO){
            if (firstNameTextFieldLiveData.value!!.text.isNotBlank() && lastNameTextFieldLiveData.value!!.text.isNotBlank() && ageTextFieldLiveData.value!!.text.isNotBlank() && midTextFieldLiveData.value!!.text.isNotBlank() && houseNumberTextFieldLiveData.value!!.text.isNotBlank() && streetTextFieldLiveData.value!!.text.isNotBlank() && villageTextFieldLiveData.value!!.text.isNotBlank()) {
                repo.postImage(
                    firstName = firstNameTextFieldLiveData.value!!.text,
                    lastName = lastNameTextFieldLiveData.value!!.text,
                    age = ageTextFieldLiveData.value!!.text,
                    mid = midTextFieldLiveData.value!!.text,
                    houseNumber = houseNumberTextFieldLiveData.value!!.text,
                    street = streetTextFieldLiveData.value!!.text,
                    village = villageTextFieldLiveData.value!!.text,
                    inputStream = image
                )
            }else{
                sendUiEvent(UiEvent.ShowSnackbar(message = "Make sure all the fields are filled properly"))
            }
        }
    }

    suspend fun sendUiEvent(uiEvent: UiEvent){
        when(uiEvent){
            is UiEvent.ShowSnackbar -> event.send(uiEvent)
        }
    }

    fun getAllWorkers() {
        viewModelScope.launch {
            getAllWorkersUseCase().onEach { res ->
                Log.d(Constants.VIEWMODEL_TAG, res.toString())
            }.launchIn(viewModelScope)
        }
    }
}

sealed class UiEvent(){
    class ShowSnackbar(val message: String) : UiEvent()
}