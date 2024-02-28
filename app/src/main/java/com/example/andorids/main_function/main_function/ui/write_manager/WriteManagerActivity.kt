package com.example.andorids.main_function.main_function.ui.write_manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.andorids.databinding.ActivityWriteManagerBinding
import com.example.andorids.main_function.main_function.ui.DTO.TypeDTO


class WriteManagerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityWriteManagerBinding.inflate(layoutInflater) }
    private lateinit var viewModel: WriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(WriteViewModel::class.java)
        val type = intent.getStringExtra("inputType")
        val position = intent.getIntExtra("inputPosition", -1)
        viewModel.receiveType.value = TypeDTO(type!!, position)





//        // FragmentManager 가져오기
//        val fragmentManager: FragmentManager = supportFragmentManager
//        // FragmentTransaction 시작
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//
//        // 첫 번째 프래그먼트를 추가하거나, replace 등의 트랜잭션 작업 수행
//
//        when("key") {
//            "recode" -> {
//                val recodeFragment = RecodeDiaryFragment()
//                fragmentTransaction.replace(R.id.id_writeManager_frameLayout, recodeFragment)
//            }
//
//            "write" -> {
//                val writeFragment = WriteDiaryFragment()
//                fragmentTransaction.replace(R.id.id_writeManager_frameLayout, writeFragment)
//            }
//        }
//
//
//        // 트랜잭션 커밋
//        fragmentTransaction.commit()


    }
}