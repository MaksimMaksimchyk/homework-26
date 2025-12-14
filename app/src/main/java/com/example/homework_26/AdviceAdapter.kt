package com.example.homework_26

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_26.AdviceAdapter.AdviceViewHolder
import com.example.homework_26.databinding.ItemAdviceBinding

class AdviceAdapter() : RecyclerView.Adapter<AdviceViewHolder>() {

    private var fullResponses = listOf<FullResponse>()

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
        holder.bind(fullResponses[position])
    }

    override fun getItemCount(): Int {
        return fullResponses.size
    }

    class AdviceViewHolder(private val binding: ItemAdviceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fullResponse: FullResponse) {
            binding.adviceText.text = fullResponse.advice
            binding.fullResponse.text = fullResponse.response
            binding.adviceCard.setOnClickListener {
                binding.fullResponse.visibility = View.VISIBLE
            }
        }
    }

    fun updateList(newFullResponse: List<FullResponse>) {
        fullResponses = newFullResponse
        notifyDataSetChanged()
    }

}