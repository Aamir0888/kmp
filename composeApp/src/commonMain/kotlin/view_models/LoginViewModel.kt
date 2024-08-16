package view_models

import utils.Graph
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import models.response.meal.MealX
import repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import models.request.login.LoginRequest
import models.response.login.LoginResponse
import utils.Response

class LoginViewModel(
    private val repository: Repository = Graph.repository
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            repository.login(loginRequest).collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _loginState.update {
                            it.copy(
                                isLoading = true, error = null
                            )
                        }
                    }

                    is Response.Success -> {
                        _loginState.update {
                            it.copy(
                                isLoading = false, error = null,
                                loginResponse = result.data
                            )
                        }
                    }

                    is Response.Error -> {
                        _loginState.update {
                            it.copy(
                                isLoading = false, error = result.error?.message
                            )
                        }
                    }
                }
            }
        }

    }
}

data class LoginState(
    val loginResponse: LoginResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)