package com.jap.interviewhomework.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jap.interviewhomework.databinding.ItemHomeBinding

class HomeAdapter(
    private val dataList: ArrayList<String>,
    private val parentview: ViewGroup
) :

    RecyclerView.Adapter<VH>() {
    private lateinit var binding : ItemHomeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val c = dataList.get(position)
        holder.itemInformation.text = c
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}

class VH(binding: ItemHomeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var itemInformation: TextView = binding.itemInformation
}
