package br.com.zup.marvel.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.marvel.ERROR
import br.com.zup.marvel.REQUIRED
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.domain.repository.AuthenticatonRepository

class LoginViewModel: ViewModel() {
    private val repository = AuthenticatonRepository()
    private var _login = MutableLiveData<User>()
    var login: LiveData<User> = _login
    private var _error = MutableLiveData<String>()
    var error:LiveData<String> = _error

    fun validate(user:User){
        when{
            user.email.isEmpty() -> _error.value = REQUIRED
            user.password.isEmpty() -> _error.value = REQUIRED
            else -> login(user)
        }
    }

    fun login(user:User){
        try{
            repository.login(user.email,user.password
            ).addOnSuccessListener { _login.value = user
            }.addOnFailureListener { _error.value = ERROR + it.message }
        }catch(e:Exception){_error.value = ERROR}
    }
}