package com.example.habittrackerproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habittrackerproject.databinding.ItemHabitBinding
import com.example.habittrackerproject.model.Habit
import androidx.navigation.findNavController

class HabitAdapter(
    val habitList: ArrayList<Habit>,
    val listener: HabitListener
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(var binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        holder.binding.habit = habit
        holder.binding.listener = listener

        val context = holder.itemView.context
        val iconResId = context.resources.getIdentifier(habit.icon, "drawable", context.packageName)
        if (iconResId != 0) {
            holder.binding.imgIcon.setImageResource(iconResId)
        } else {
            holder.binding.imgIcon.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = habitList.size

    fun updateHabitList(newHabitList: List<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }
}