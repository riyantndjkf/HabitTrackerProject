package com.project.anmpc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.project.anmpc.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding

        // Variabel untuk menyimpan logika permainan
        private var score = 0
        private var num1 = 0
        private var num2 = 0

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentGameBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtTurn.text = "$playerName's Turn"

            // Mulai game dengan memunculkan soal pertama
            generateQuestion()
            binding.btnSubmit.setOnClickListener {
                val answer = binding.txtAnswer.text.toString().toIntOrNull()

                if (answer == (num1 + num2)) {
                    score++
                    generateQuestion()
                } else {
                    val action = GameFragmentDirections.actionResultFragment(score)
                    it.findNavController().navigate(action)
                }
            }
        }

        private fun generateQuestion() {
            num1 = (0..100).random()
            num2 = (0..100).random()
            binding.txtQuestion.text = "$num1 + $num2"
            binding.txtAnswer.setText("")
        }
}