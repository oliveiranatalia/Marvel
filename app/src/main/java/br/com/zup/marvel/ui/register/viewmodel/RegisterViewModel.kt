package br.com.zup.marvel.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.marvel.ERROR
import br.com.zup.marvel.REQUIRED
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.domain.repository.AuthenticatonRepository

class RegisterViewModel:ViewModel() {
    private val repository = AuthenticatonRepository()

    private var _register = MutableLiveData<User>()
    var register: LiveData<User> = _register

    private var _error = MutableLiveData<String>()
    var error: LiveData<String> = _error

    fun validate(user:User){
        when{
            user.email.isEmpty() -> _error.value = REQUIRED
            user.password.isEmpty() -> _error.value = REQUIRED
            else -> register(user)
        }
    }
    private fun register(user:User){
        try {
            repository.register(user.email, user.password)
            _register.value = user
        }catch(e:Exception){
            _error.value = ERROR
        }
    }
}