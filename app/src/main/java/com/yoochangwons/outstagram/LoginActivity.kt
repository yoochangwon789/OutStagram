package com.yoochangwons.outstagram

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yoochangwons.outstagram.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            (application as MasterApplication).service.login(
                binding.loginInputId.text.toString(), binding.loginInputPassword.text.toString()
            ).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@LoginActivity, "로그인 되었습니다.",
                            Toast.LENGTH_LONG
                        ).show()
                        (application as MasterApplication).createNetworkRetrofit()
                        startActivity(
                            Intent(this@LoginActivity, OutStagramPostListActivity::class.java)
                        )
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                }
            })
        }
    }

    fun saveUserId(userId: String) {
        val sp = getSharedPreferences("userId", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("userId", userId)
        editor.apply()
    }
}