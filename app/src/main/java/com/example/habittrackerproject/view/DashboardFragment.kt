package com.example.habittrackerproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittrackerproject.databinding.FragmentDashboardBinding
import com.example.habittrackerproject.model.Habit
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
        habitAdapter = HabitAdapter(
            habitList = arrayListOf(),
            listener = object : HabitListener {

                override fun onPlusClick(habit: Habit) {
                    val progress = habit.progress.toInt()
                    val target = habit.target.toInt()

                    if (progress < target) {
                        habit.progress = (progress + 1).toString()

                        if (progress + 1 >= target) {
                            habit.status = "Completed"
                        }

                        viewModel.updateProgress(habit)
                        habitAdapter.notifyDataSetChanged()
                    }
                }

                override fun onMinusClick(habit: Habit) {
                    val progress = habit.progress.toInt()

                    if (progress > 0) {
                        habit.progress = (progress - 1).toString()

                        if (progress - 1 < habit.target.toInt()) {
                            habit.status = "In Progress"
                        }

                        viewModel.updateProgress(habit)
                        habitAdapter.notifyDataSetChanged()
                    }
                }
            }
        )

        binding.recyclerHabit.layoutManager = LinearLayoutManager(context)
        binding.recyclerHabit.adapter = habitAdapter
        viewModel.refresh()
        observeViewModel()
        binding.fabAdd.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToCreateHabitFragment()
            findNavController().navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.habitLD.observe(viewLifecycleOwner) { habitList ->
            habitAdapter.updateHabitList(habitList)
        }
    }
}