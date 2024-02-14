package com.example.andorids.main_function.main_function.ui.write_manager.recode

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.andorids.R
import com.example.andorids.databinding.FragmentRecodeDiaryBinding
import com.example.andorids.main_function.ui.notifications.ProfileViewModel

class RecodeDiaryFragment : Fragment() {

    private var _binding: FragmentRecodeDiaryBinding? = null
    private val binding get() = _binding!!

    private lateinit var speechRecognizer: SpeechRecognizer
    private var isRecoding = false
    var recodeStr = "" // 음성인식을 두번 연속 할 때 기존 인식된 텍스트를 초기화하는 코드

    var firstText = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding = FragmentRecodeDiaryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 권한 설정
        requestPermission()

        binding.idRecodeToolBar.setNavigationOnClickListener {
            requireActivity().finish()
        }


        binding.idRecodeRecodeButton.setOnClickListener {
            Log.d("RecodeDiaryFragment", "recodeStr : $recodeStr")
            if (isRecoding) {
                pauseVoiceRecording()
            } else {
                startVoiceRecording()
            }
        }

        return root
    }

    // 권한 설정 메소드
    private fun requestPermission() {
        // 버전 체크, 권한 허용했는지 체크
        if (Build.VERSION.SDK_INT >= 23 &&
            ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.RECORD_AUDIO
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.RECORD_AUDIO),
                0
            )
        }
    }

    private var isRecordingPaused = false

    private fun pauseVoiceRecording() {
        // 녹음 일시 정지 로직을 구현합니다.
        // 예를 들어, 녹음 일시 정지 상태에 해당하는 UI 변경 및 녹음 일시 정지 로직을 여기에 추가합니다.

        // UI 변경 예시:
        binding.idRecodeRecodeButton.setImageResource(R.drawable.ic_play_recode)
        isRecoding = false

        // 녹음 일시 정지에 대한 추가 로직을 구현하세요.
        isRecordingPaused = true
        recodeStr = binding.idRecodeRecodingText.text.toString()

        speechRecognizer.stopListening()
    }

    private fun startVoiceRecording() {
        if (firstText) {
            binding.idRecodeFirstText.visibility = View.GONE
        }
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, requireContext().packageName)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")
        }


        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        isRecoding = true

        if (isRecordingPaused) {
            // 녹음이 일시 정지된 상태에서 다시 시작할 때 저장한 내용을 이어서 녹음합니다.
            recodeStr += " " + binding.idRecodeRecodingText.text
            isRecordingPaused = false
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(activity)
        speechRecognizer.setRecognitionListener(recognitionListener)
        speechRecognizer.startListening(intent)
    }

    private val recognitionListener: RecognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) {
            Toast.makeText(activity, "음성인식 시작", Toast.LENGTH_SHORT).show()
            Log.d("asdfasdfasdfasdf1", "${binding.idRecodeRecodingText.text}")
        }

        override fun onBeginningOfSpeech() {
            binding.idRecodeRecodeButton.setImageResource(R.drawable.ic_pause_recode)
            Log.d("asdfasdfasdfasdf2", "${binding.idRecodeRecodingText.text}")

        }

        override fun onRmsChanged(rmsdB: Float) {
            Log.d("asdfasdfasdfasdf3", "${binding.idRecodeRecodingText.text}")

        }

        override fun onBufferReceived(buffer: ByteArray?) {
            Log.d("asdfasdfasdfasdf4", "${binding.idRecodeRecodingText.text}")

        }

        override fun onEndOfSpeech() {
            Log.d("asdfasdfasdfasdf5", "${binding.idRecodeRecodingText.text}")
            startVoiceRecording()
        }

        override fun onError(error: Int) {
            when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "오디오 에러입니다."
                SpeechRecognizer.ERROR_CLIENT -> "클라이언트 에러입니다."
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "퍼미션이 없습니다."
                SpeechRecognizer.ERROR_NETWORK -> "네트워크 에러입니다."
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "네트워크 타임아웃입니다."
                SpeechRecognizer.ERROR_NO_MATCH -> "찾을 수 없습니다."
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> {
                    startVoiceRecording()
                }
                SpeechRecognizer.ERROR_SERVER -> "서버가 오류입니다."
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "녹음 시간이 초과되었습니다."
                else -> "오류! 개발자에게 문의해주세요."
            }
        }

        override fun onResults(results: Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)

            if (matches != null && matches.isNotEmpty()) {
                // 전체 결과를 받아오는 부분에서 수정
                val latestResult = matches[matches.size - 1]
                recodeStr = latestResult

                requireActivity().runOnUiThread {
                    binding.idRecodeRecodingText.text = binding.idRecodeRecodingText.text.toString() + recodeStr
                }

                Log.d("TEXTasdf", "$recodeStr")
            } else {
                binding.idRecodeRecodingText.text = "음성을 인식하지 못했습니다."
            }
        }

        override fun onPartialResults(partialResults: Bundle?) {
        }



        override fun onEvent(eventType: Int, params: Bundle?) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // SpeechRecognizer 종료
        speechRecognizer.destroy()
    }
}
