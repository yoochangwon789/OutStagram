package com.yoochangwons.outstagram

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.opengl.GLES30
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.yoochangwons.outstagram.databinding.ActivityOutStagramBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OutStagramActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutStagramBinding

    private lateinit var inputId: EditText
    private lateinit var passwordOne: EditText
    private lateinit var passwordTwo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStagramBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
        setOnClick(this)
    }

    fun initView() {
        inputId = binding.inputId
        passwordOne = binding.inputPasswordOne
        passwordTwo = binding.inputPasswordTwo
    }

    fun setOnClick(activity: Activity) {
        binding.signUp.setOnClickListener {
            register(activity)
        }

        binding.loginUp.setOnClickListener {
            val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
            val token = sp.getString("login_sp", "token 이 존재하지 않습니다.")
            Log.d("abccc", "token : $token")
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
        return inputId.text.toString()
    }

    fun getPasswordOne(): String {
        return passwordOne.text.toString()
    }

    fun getPasswordTwo(): String {
        return passwordTwo.text.toString()
    }
}