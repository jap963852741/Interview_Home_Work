package com.jap.interviewhomework.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jap.interviewhomework.Repository.bean.News
import com.jap.interviewhomework.databinding.ItemHomeBinding

class HomeAdapter(
    private val dataList: ArrayList<News>?
) :

    RecyclerView.Adapter<VH>() {
    private lateinit var binding : ItemHomeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val news = dataList!!.get(position)
        holder.chtMessage.text = news.chtmessage
        if (news.engmessage != "")
            holder.engMessage.text =  news.engmessage
        holder.startTime.text =  news.starttime
        holder.endTime.text =  news.endtime
        holder.updateTime.text = news.updatetime
        holder.content.text = news.content
        if (news.url != null)
            holder.url.text =  news.url

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
