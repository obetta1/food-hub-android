package com.example.foodhumandroid.ui.features.aut.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodhumandroid.data.repository.FoodApi
import com.example.foodhumandroid.data.models.SignUpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewmodel@Inject constructor(val foodApi: FoodApi):ViewModel() {
    private val _uiState = MutableStateFlow<SignUpEvent>(SignUpEvent.Empty)
    var uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<SignupNavigationEvent>()
    var navigationState = _navigationEvent.asSharedFlow()

    private val _email = MutableStateFlow("")
    var email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    var password = _password.asStateFlow()

    private val _name = MutableStateFlow("")
    var name = _name.asStateFlow()

    fun onEmailChange(email:String){
        _email.value = email
    }
    fun onPasswordChange(password:String){
        _password.value = password
    }

    fun onNameChange(name:String){
        _name.value = name
    }

    fun onSubmit(){
        viewModelScope.launch {
            try {
                _uiState.value = SignUpEvent.Loading
                val response = foodApi.signUp(
                    SignUpRequest(
                        name = name.value,
                        email = email.value,
                        password = password.value
                    )
                ).body()
                if (response?.token.isNullOrEmpty()) {
                    _uiState.value = SignUpEvent.Success
                    _navigationEvent.tryEmit(SignupNavigationEvent.NavigateToHome)

                }
            }catch (e:Exception){
                e.printStackTrace()
                _uiState.value = SignUpEvent.Error
            }
        }

    }


    sealed class SignupNavigationEvent{
        object NavigateToLogin : SignupNavigationEvent()
        object  NavigateToHome: SignupNavigationEvent()
    }

    sealed class SignUpEvent{
        object Success: SignUpEvent()
        object Error: SignUpEvent()
        object Loading: SignUpEvent()
        object Empty: SignUpEvent()
    }


}