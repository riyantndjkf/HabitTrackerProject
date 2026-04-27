package com.example.habittrackerproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittrackerproject.databinding.FragmentDashboardBinding
import com.example.habittrackerproject.viewmodel.HabitViewModel

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: HabitViewModel
    private lateinit var habitAdapter: HabitAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        habitAdapter = HabitAdapter(arrayListOf()) { habit ->
            viewModel.updateProgress(habit)
        }

        binding.recyclerHabit.layoutManager = LinearLayoutManager(context)
        binding.recyclerHabit.adapter = habitAdapter
        viewModel.refresh()
        observeViewModel()
        binding.fabAdd.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToCreateHabitFragment()
            it.findNavController().navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.habitLD.observe(viewLifecycleOwner) { habitList ->
            habitAdapter.updateHabitList(habitList)
        }
    }
}