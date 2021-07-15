package com.yoochangwons.outstagram

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
    }
}