package com.example.habittrackerproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittrackerproject.databinding.FragmentCreateHabitBinding
import com.example.habittrackerproject.model.Habit
import com.example.habittrackerproject.viewmodel.HabitViewModel

class CreateHabitFragment : Fragment() {

    private lateinit var binding: FragmentCreateHabitBinding
    private lateinit var viewModel: HabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        val iconList = arrayOf("water", "dumbbell", "book", "meditation", "game", "food")
        val spinnerAdapter = android.widget.ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            iconList
        )
        binding.spCategory.adapter = spinnerAdapter
        binding.btnSave.setOnClickListener {
            val title = binding.txtTitle.text.toString()
            val description = binding.txtDescription.text.toString()
            val targetStr = binding.txtTarget.text.toString()
            val selectedIcon = binding.spCategory.selectedItem.toString()

            if (title.isNotEmpty() && targetStr.isNotEmpty()) {
                val newHabit = Habit(
                    id = "",
                    title = title,
                    description = description,
                    target = targetStr.toInt(),
                    progress = 0,
                    icon = selectedIcon,
                    status = "In Progress"
                )
                viewModel.addHabit(newHabit)
                Toast.makeText(context, "Habit berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                val action = CreateHabitFragmentDirections.actionCreateHabitFragmentToDashboardFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Judul dan Target harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}