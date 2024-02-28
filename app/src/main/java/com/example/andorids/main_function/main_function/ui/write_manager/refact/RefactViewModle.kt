package com.example.andorids.main_function.main_function.ui.write_manager.refact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RefactViewModle : ViewModel() {
    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int> = _position
}