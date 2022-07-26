package br.com.zup.marvel.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.zup.marvel.USER
import br.com.zup.marvel.databinding.ActivityLoginBinding
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.ui.login.viewmodel.LoginViewModel
import br.com.zup.marvel.ui.register.view.RegisterActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observer()
        binding.tvCriarConta.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.bvBotaoLogin.setOnClickListener{
            goToLogin()
        }
    }
    private fun observer(){
        viewModel.login.observe(this){goToHome(it)}
        viewModel.error.observe(this){ Snackbar.make(binding.root,it, Snackbar.LENGTH_SHORT).show()
        }
    }
    private fun goToLogin(){
        val user = getData()
        viewModel.validate(user)
    }
    private fun getData(): User{
        return User(binding.etEmailLogin.text.toString(),binding.etSenhaLogin.text.toString())
    }
    private fun goToHome(user:User){
        startActivity(Intent(this, HomeActivity::class.java).apply { putExtra(USER,user)})
    }
}