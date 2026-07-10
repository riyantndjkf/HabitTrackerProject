package com.example.habittrackerproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        
        val habitId = arguments?.getString("habitId") ?: ""
        val icons = arrayOf("Book", "Water", "Dumbbell", "Meditation", "Game", "Food")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, icons)
        binding.spinnerIcon.adapter = adapter

        // Fetch real habit instead of using dummy
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val dbHabit = viewModel.getHabitById(habitId)
            
            withContext(Dispatchers.Main) {
                if (dbHabit != null) {
                    binding.habit = dbHabit
                    binding.lifecycleOwner = viewLifecycleOwner

                    val iconIndex = icons.indexOfFirst { it.equals(dbHabit.icon, ignoreCase = true) }
                    if (iconIndex >= 0) binding.spinnerIcon.setSelection(iconIndex)
                }
            }
        }
        
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
