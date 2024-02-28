package com.example.andorids.main_function.main_function.ui.write_manager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.andorids.main_function.main_function.ui.DTO.TypeDTO

class WriteViewModel : ViewModel() {
    val receiveType = MutableLiveData<TypeDTO>().apply { value = TypeDTO("loading") }
}