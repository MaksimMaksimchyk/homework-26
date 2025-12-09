package com.example.homework_26

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.contains
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_26.AdviceAdapter.AdviceViewHolder
import com.example.homework_26.databinding.ItemAdviceBinding

class AdviceAdapter() : RecyclerView.Adapter<AdviceViewHolder>() {

    private var advices = listOf<Advice>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdviceViewHolder {
        val binding = ItemAdviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdviceViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AdviceViewHolder,
        position: Int
    ) {
        holder.bind(advices[position])
    }

    override fun getItemCount(): Int {
        return advices.size
    }

    class AdviceViewHolder(private val binding: ItemAdviceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(advice: Advice) {
            binding.adviceText.text = advice.advice
        }
    }

    fun updateList(newAdvice: List<Advice>) {
        advices = newAdvice
        notifyDataSetChanged()
    }

}