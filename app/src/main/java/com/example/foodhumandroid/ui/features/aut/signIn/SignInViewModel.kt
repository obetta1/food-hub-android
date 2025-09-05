package com.example.foodhumandroid.ui.features.aut.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodhumandroid.data.models.SignInResquest
import com.example.foodhumandroid.data.repository.FoodApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(val foodApi: FoodApi):ViewModel() {
    private val _uiState = MutableStateFlow<SignInEvent>(SignInEvent.Empty)
    var uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<SignInNavigationEvent>()
    var navigationState = _navigationEvent.asSharedFlow()

    private val _email = MutableStateFlow("")
    var email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    var password = _password.asStateFlow()



    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

        fun onLoginPressed() {
            viewModelScope.launch {
                try {
                    val response = foodApi.signIn(SignInResquest(email = _email.value, password = _password.value)).body()
                    _navigationEvent.tryEmit(
                        SignInNavigationEvent.NavigateToHome
                    )
                }catch (e:Exception){
                    e.printStackTrace()
                    _uiState.value = SignInEvent.Error
                }

            }
        }



    sealed class SignInNavigationEvent{
        object NavigateToLogin : SignInNavigationEvent()
        object  NavigateToHome: SignInNavigationEvent()
    }

    sealed class SignInEvent{
        object Success: SignInEvent()
        object Error: SignInEvent()
        object Loading: SignInEvent()
        object Empty: SignInEvent()
    }
}
