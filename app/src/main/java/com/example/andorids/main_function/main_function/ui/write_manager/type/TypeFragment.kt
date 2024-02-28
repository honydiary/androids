package com.example.andorids.main_function.main_function.ui.write_manager.type

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.andorids.R
import com.example.andorids.databinding.FragmentTypeBinding
import com.example.andorids.main_function.main_function.ui.write_manager.WriteViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [TypeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TypeFragment : Fragment() {
    private var _binding: FragmentTypeBinding? = null
    private val TAG = "TypeFragment"
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<WriteViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTypeBinding.inflate(inflater, container, false)
        Log.d(TAG, "state : in")
        viewModel.receiveType.observe(viewLifecycleOwner){ data ->
            Log.d(TAG, "state : $data")
            when(data.type){
                "write" -> findNavController().navigate(R.id.action_typeFragment_to_writeDiaryFragment2)
                "recode" -> findNavController().navigate(R.id.action_typeFragment_to_recodeDiaryFragment)
                "refact" -> findNavController().navigate(R.id.action_typeFragment_to_refactFragment)
                "view" -> findNavController().navigate(R.id.action_typeFragment_to_contentFragment)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}