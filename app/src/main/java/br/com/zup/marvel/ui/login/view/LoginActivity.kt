package br.com.zup.marvel.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.marvel.databinding.ActivityLoginBinding
import br.com.zup.marvel.ui.register.view.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCriarConta.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}