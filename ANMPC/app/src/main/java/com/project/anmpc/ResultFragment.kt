package com.project.anmpc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.project.anmpc.databinding.FragmentGameBinding
import com.project.anmpc.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerScore =
            ResultFragmentArgs.fromBundle(requireArguments()).playerScore
        binding.txtScore.text = "Your Score is $playerScore"

        binding.btnBack.setOnClickListener {
            val action = ResultFragmentDirections.actionMainFragment()
            //Navigation.findNavController(it).navigate(action)
            it.findNavController().navigate(action)
        }
    }
}