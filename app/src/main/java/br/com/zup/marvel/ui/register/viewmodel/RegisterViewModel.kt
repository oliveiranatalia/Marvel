package br.com.zup.marvel.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.marvel.CREATE_ERROR
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
            user.name.isEmpty() -> _error.value = REQUIRED
            user.email.isEmpty() -> _error.value = REQUIRED
            user.password.isEmpty() -> _error.value = REQUIRED
            else -> register(user)
        }
    }
    private fun register(user:User){
        try {
            repository.register(user.email, user.password
            ).addOnSuccessListener { repository.updateUser(user.name)?.addOnSuccessListener { _register.value = user }
            }.addOnFailureListener{_error.value = CREATE_ERROR + it.message}
        }catch(e:Exception){
            _error.value = e.message
        }
    }
}