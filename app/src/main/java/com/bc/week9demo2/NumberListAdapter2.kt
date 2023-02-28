package com.bc.week9demo2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NumberListAdapter2 : ListAdapter<Int, NumberListAdapter2.IntViewHolder>(RowItemDiffCallback()){

    fun setData(data: List<Int>){
        submitList(data)
    }

    class IntViewHolder (val row: View):RecyclerView.ViewHolder(row)  {
        val textView = row.findViewById<TextView>(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        val holder = IntViewHolder(layout)
        holder.row.setOnClickListener {
            Log.v("onClick", it.findViewById<TextView>(R.id.number).text.toString())
        }

        return holder

    }

    override fun onBindViewHolder(holder: IntViewHolder, position: Int) {
        holder.textView.text = getItem(position).toString()
    }
}

class RowItemDiffCallback: DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        Log.v("callback areItemsTheSame", Thread.currentThread().name)
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        Log.v("callback areContentsTheSame", Thread.currentThread().name)
        return oldItem == newItem
    }
}