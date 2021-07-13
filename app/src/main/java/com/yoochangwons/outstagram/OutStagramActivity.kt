package com.yoochangwons.outstagram

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.yoochangwons.outstagram.databinding.ActivityOutStagramBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OutStagramActivity : AppCompatActivity() {

    lateinit var binding: ActivityOutStagramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStagramBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun setOnClick() {
        binding.signUp.setOnClickListener {

        }

        binding.loginUp.setOnClickListener {

        }
    }

    fun register(activity: Activity) {
        (application as MasterApplication).service
            .createUser(getId(), getPasswordOne(), getPasswordTwo())
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(activity, "가입에 성공했습니다.", Toast.LENGTH_LONG).show()
                        val user = response.body()
                        val token = user!!.token!!
                        setToken(token)
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(activity, "가입에 실패 했습니다.", Toast.LENGTH_LONG).show()
                }
            })
    }

    @SuppressLint("CommitPrefEdits")
    fun setToken(token: String) {
        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("login_sp", token)
        editor.apply()
    }

    fun getId(): String {
        return binding.inputId.text.toString()
    }

    fun getPasswordOne(): String {
        return binding.inputPasswordOne.text.toString()
    }

    fun getPasswordTwo(): String {
        return binding.inputPasswordTwo.text.toString()
    }
}