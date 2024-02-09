package com.example.andorids.main_function.main_function.ui.recode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.andorids.databinding.ActivityWriteManagerBinding

class WriteManagerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityWriteManagerBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}