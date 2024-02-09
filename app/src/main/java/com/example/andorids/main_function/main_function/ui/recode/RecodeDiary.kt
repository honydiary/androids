package com.example.andorids.main_function.ui.recode

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.andorids.databinding.ActivityHomeBinding
import com.example.andorids.databinding.FragmentRecodeDiaryBinding


class RecodeDiary : Fragment() {

    private val binding by lazy { FragmentRecodeDiaryBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.idRecodeToolBar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }


        return binding.root
    }

}