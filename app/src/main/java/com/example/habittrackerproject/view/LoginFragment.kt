package com.example.habittrackerproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Button
import com.example.habittrackerproject.R


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
//
//        btnLogin.setOnClickListener {
//            findNavController().navigate(R.id.action_login_to_dashboard)
//        }
//    }

    companion object {

    }
}