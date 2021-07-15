package com.yoochangwons.outstagram

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yoochangwons.outstagram.databinding.ActivityOutStagrmLoginInfoBinding

class OutStagrmLoginInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutStagrmLoginInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStagrmLoginInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.userInfoLogoutBtn.setOnClickListener {

        }
    }

    fun logoutCheck() {
        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("login_sp", "null")
        editor.apply()
    }
}