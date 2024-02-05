package com.example.andorids.main_function.login_function

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.andorids.databinding.ActivityStartBinding
import com.example.andorids.main_function.main_function.ui.HomeActivity

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    val TAG ="StartActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "test")

        startActivity(Intent(this, HomeActivity::class.java))
    }
}