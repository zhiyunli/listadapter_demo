package com.bc.week9demo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberListAdapter : RecyclerView.Adapter<NumberListAdapter.IntViewHolder>(){

    private val data = mutableListOf<Int>()

    fun setData(data: List<Int>){
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    class IntViewHolder (val row: View):RecyclerView.ViewHolder(row)  {
        val textView = row.findViewById<TextView>(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        val holder = IntViewHolder(layout)

        return holder

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: IntViewHolder, position: Int) {
        holder.textView.text = data[position].toString()
    }

}