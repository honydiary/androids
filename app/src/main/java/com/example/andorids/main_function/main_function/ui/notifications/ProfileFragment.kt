package com.example.andorids.main_function.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.andorids.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root



//        binding.idProfileSeeGoodWordButton.setOnClickListener {
//            val recodeDiary = RecodeDiary()
//            addFragment(recodeDiary)
//        }

        return root
    }

//    private fun addFragment(fragment: Fragment) {
//        // FragmentManager를 사용하여 FragmentTransaction 시작
//        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//
//        // 새로운 Fragment를 위에 추가 (기존의 Fragment는 숨김)
//        fragmentTransaction.replace(R.id.nav_host_fragment_activity_home, fragment)
//        fragmentTransaction.addToBackStack(null) // 백스택에 추가하여 뒤로가기 가능하도록 함
//
//        // FragmentTransaction을 커밋하여 변경 사항을 적용
//        fragmentTransaction.commit()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}