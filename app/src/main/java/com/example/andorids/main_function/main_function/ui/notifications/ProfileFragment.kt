package com.example.andorids.main_function.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.andorids.databinding.FragmentProfileBinding
import com.example.andorids.main_function.main_function.ui.notifications.MyAdapter


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        recyclerView = binding.idProfileGoodWordRecycler
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // 테스트 데이터
        val data = listOf("많은 사람은 TV 앞에 앉아서, 혹은 핸드폰을 만지는 것 이외에는 많은 일에 도전하지 않는다. 너도 마찬가지다. 제발 좀 움직이기라도 해라. 명언만 찾아다니지 말고;; ㅇㅈ?", "사람은 변화하고, 발전한다. 그런데 너는 대체 왜...", "속임수는 병법의 기본이다. 그러게 왜 속고 그러냐.", "상처는 뼈에 새기고, 은혜는 뭐 꼭 안 새겨고 될듯.")

        // 어댑터 설정
        adapter = MyAdapter(data)
        recyclerView.adapter = adapter

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}