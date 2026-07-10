package com.example.habittrackerproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittrackerproject.databinding.FragmentEditHabitBinding
import com.example.habittrackerproject.model.Habit
import com.example.habittrackerproject.viewmodel.HabitViewModel

class EditHabitFragment : Fragment() {

    private lateinit var binding: FragmentEditHabitBinding
    private lateinit var viewModel: HabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        
        // Mocking retrieving habit for Two-way binding demo.
        // In real use case, fetch from db/viewModel using habitId provided via arguments
        val habitId = arguments?.getString("habitId") ?: ""
        
        val tempHabit = Habit(
            id = habitId,
            title = "Read Books",
            description = "Expand your knowledge",
            target = "20",
            unit = "pages",
            progress = "0",
            icon = "books",
            status = "In Progress"
        )
        
        binding.habit = tempHabit
        
        binding.lifecycleOwner = viewLifecycleOwner

        // Setup Icon Spinner
        val icons = arrayOf("Book", "Water", "Dumbbell", "Meditation", "Game", "Food")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, icons)
        binding.spinnerIcon.adapter = adapter
        
        binding.spinnerIcon.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedIcon = icons[position].lowercase()
                binding.habit?.icon = selectedIcon
                binding.invalidateAll()
            }
            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {}
        }
        
        binding.btnSubmit.setOnClickListener {
            // "Eksekusi Update: Memastikan eksekusi @Update dipanggil melalui ViewModel agar perubahan pada Room tersimpan sempurna."
            viewModel.updateHabitRoom(binding.habit!!)
            findNavController().navigateUp()
        }
    }
}
