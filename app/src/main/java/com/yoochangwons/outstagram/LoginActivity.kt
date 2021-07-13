package com.yoochangwons.outstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yoochangwons.outstagram.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signUp.setOnClickListener {
            startActivity(
                Intent(this, OutStagramActivity::class.java)
            )
        }

        binding.loginUp.setOnClickListener {
            (application as MasterApplication).service
        }
    }
}