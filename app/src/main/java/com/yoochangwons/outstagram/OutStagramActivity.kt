package com.yoochangwons.outstagram

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yoochangwons.outstagram.databinding.ActivityOutStagramBinding

class OutStagramActivity : AppCompatActivity() {

    lateinit var binding: ActivityOutStagramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStagramBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun initView() {
        val userId = getId()
        val passwordOne = getPasswordOne()
        val passwordTwo = getPasswordTwo()
    }

    fun setOnClick() {
        binding.signUp.setOnClickListener {

        }

        binding.loginUp.setOnClickListener {

        }
    }

    fun register(activity: Activity) {

    }

    @SuppressLint("CommitPrefEdits")
    fun setToken(token: String) {
        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()

        editor.putString("login_sp", token)
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