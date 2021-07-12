package com.yoochangwons.outstagram

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
}