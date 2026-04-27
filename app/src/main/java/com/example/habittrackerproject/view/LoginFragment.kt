package com.example.habittrackerproject.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.habittrackerproject.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("HabitPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("is_logged_in", false)

        if (isLoggedIn) {
            val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
            findNavController().navigate(action)
        }
        binding.loginBtn.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            if (username == "student" && password == "123") {
                val editor = sharedPref.edit()
                editor.putBoolean("is_logged_in", true)
                editor.apply()
                val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
                it.findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Username atau Password salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}