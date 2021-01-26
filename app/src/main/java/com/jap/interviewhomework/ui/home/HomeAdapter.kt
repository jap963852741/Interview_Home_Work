package com.jap.interviewhomework.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jap.interviewhomework.data.model.News
import com.jap.interviewhomework.databinding.ItemHomeBinding

class HomeAdapter(
    private val dataList: ArrayList<News>?,
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
        val c = dataList!!.get(position)
        holder.chtMessage.text = c.chtmessage
        if (c.engmessage != "")
            holder.engMessage.text =  c.engmessage
        holder.startTime.text =  c.starttime
        holder.endTime.text =  c.endtime
        holder.updateTime.text = c.updatetime
        holder.content.text = c.content
        if (c.url != null)
            holder.url.text =  c.url

    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

}

class VH(binding: ItemHomeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var chtMessage: TextView = binding.chtMessage
    var engMessage: TextView = binding.engMessage
    var startTime: TextView = binding.startTime
    var endTime: TextView = binding.endTime
    var updateTime: TextView = binding.updateTime
    var content: TextView = binding.content
    var url: TextView = binding.url

}
