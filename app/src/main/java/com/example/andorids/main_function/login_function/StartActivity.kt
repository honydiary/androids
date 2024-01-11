package com.example.andorids.main_function.login_function

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.andorids.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}