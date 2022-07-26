package br.com.zup.marvel.ui.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.zup.marvel.USER
import br.com.zup.marvel.databinding.ActivityRegisterBinding
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.ui.register.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by lazy { ViewModelProvider(this)[RegisterViewModel::class.java]}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observer()
        binding.bvBotaoRegistro.setOnClickListener{register()}
    }
    private fun observer(){
        viewModel.register.observe(this){goToHome(it)}
        viewModel.error.observe(this){ Snackbar.make(binding.root,it, Snackbar.LENGTH_SHORT).show()}

    }    private fun register(){
        val user = getData()
        viewModel.validate(user)
    }
    private fun getData():User{
        return User(
            binding.etEmailRegistro.text.toString(),
            binding.etSenhaRegistro.text.toString()
        )
    }
    private fun goToHome(user:User){
        startActivity(Intent(this, HomeActivity::class.java).apply { putExtra(USER,user) })
    }
}