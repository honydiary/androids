package com.example.andorids.main_function.main_function.ui.write_manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WriteViewModel : ViewModel() {
    val receiveType = MutableLiveData<String>().apply { value = "lodding" }
}