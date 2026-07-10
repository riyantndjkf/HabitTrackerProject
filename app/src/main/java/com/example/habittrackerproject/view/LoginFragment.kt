package com.example.habittrackerproject.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.habittrackerproject.database.AppDatabase
import com.example.habittrackerproject.databinding.FragmentLoginBinding
import com.example.habittrackerproject.model.User
import com.example.habittrackerproject.util.buildDb
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = buildDb(requireContext())

        val sharedPref =
            requireActivity().getSharedPreferences(
                "HabitPrefs",
                Context.MODE_PRIVATE
            )

        // Auto login
        val isLoggedIn = sharedPref.getBoolean("is_logged_in", false)

        if (isLoggedIn) {
            val action =
                LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
            findNavController().navigate(action)
            return
        }

        // Insert user default sekali saja
        lifecycleScope.launch(Dispatchers.IO) {

            val existingUser =
                db.userDao().getUserByUsername("student")

            if (existingUser == null) {

                db.userDao().insert(
                    User(
                        username = "student",
                        password = "123"
                    )
                )
            }
        }

        binding.loginBtn.setOnClickListener {

            val username =
                binding.txtUsername.text.toString().trim()

            val password =
                binding.txtPassword.text.toString().trim()

            lifecycleScope.launch(Dispatchers.IO) {
                val user = db.userDao().login(username, password)

                withContext(Dispatchers.Main) {
                    if (user != null) {
                        sharedPref.edit()
                            .putBoolean("is_logged_in", true)
                            .apply()

                        val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(
                            context,
                            "Username atau Password salah!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}