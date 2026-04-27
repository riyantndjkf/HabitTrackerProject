package com.example.habittrackerproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habittrackerproject.databinding.ItemHabitBinding
import com.example.habittrackerproject.model.Habit

class HabitAdapter(
    val habitList: ArrayList<Habit>,
    val onAddClick: (Habit) -> Unit,
    val onMinusClick: (Habit) -> Unit
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(var binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        holder.binding.txtTitle.text = habit.title
        holder.binding.txtDescription.text = habit.description
        holder.binding.txtProgress.text = "${habit.progress} / ${habit.target}"
        holder.binding.tvStatus.text = habit.status
        holder.binding.progressBar.max = habit.target
        holder.binding.progressBar.progress = habit.progress
        val context = holder.itemView.context
        val iconResId = context.resources.getIdentifier(habit.icon, "drawable", context.packageName)
        if (iconResId != 0) {
            holder.binding.imgIcon.setImageResource(iconResId)
        } else {
            holder.binding.imgIcon.setImageResource(android.R.drawable.ic_menu_gallery)
        }
        holder.binding.btnPlus.setOnClickListener {
            if (habit.progress < habit.target) {
                habit.progress += 1
                if (habit.progress >= habit.target) {
                    habit.status = "Completed"
                }
                onAddClick(habit)
                notifyItemChanged(position)
            }
        }
        holder.binding.btnMinus.setOnClickListener {
            if (habit.progress > 0) {
                habit.progress -= 1
                if (habit.progress < habit.target) {
                    habit.status = "In Progress"
                }
                onMinusClick(habit)
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = habitList.size

    fun updateHabitList(newHabitList: List<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }
}