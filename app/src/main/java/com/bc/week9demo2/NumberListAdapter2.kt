package com.bc.week9demo2

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bc.week9demo2.databinding.ColorViewBinding
import com.bc.week9demo2.databinding.ItemViewBinding

enum class ITEM_VIEW_TYPE {NUMBER, COLOR}

class NumberListAdapter2 : ListAdapter<Any, RecyclerView.ViewHolder>(RowItemDiffCallback()){

    fun setData(data: List<Any>){
        submitList(data)
    }

    class IntViewHolder private constructor(val binding: ItemViewBinding):RecyclerView.ViewHolder(binding.root)  {
        companion object{
            fun from(parent: ViewGroup):IntViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding:ItemViewBinding  = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_view, parent, false)
                //val binding = ItemViewBinding.inflate(layoutInflater, parent, false)
                return IntViewHolder(binding)
            }

        }
    }

    class ColorViewHolder private constructor(val binding: ColorViewBinding):   RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ColorViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ColorViewBinding.inflate(layoutInflater,
                    parent, false)
                return ColorViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE.NUMBER.ordinal -> IntViewHolder.from(parent)
            else ->ColorViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //holder.textView.text = getItem(position).toString()

        when(holder){
            is IntViewHolder ->{
                holder.binding.num = getItem(position) as Int
                holder.binding.executePendingBindings()
            }
            is ColorViewHolder ->{
                holder.binding.color = getItem(position) as Color
                holder.binding.executePendingBindings()
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is Int -> ITEM_VIEW_TYPE.NUMBER.ordinal
            else -> ITEM_VIEW_TYPE.COLOR.ordinal
        }
    }
}

class RowItemDiffCallback: DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        Log.v("callback areItemsTheSame", Thread.currentThread().name)
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        Log.v("callback areContentsTheSame", Thread.currentThread().name)
        if(oldItem is Int && newItem is Int)
            return oldItem == newItem

        if(oldItem is Color && newItem is Color)
            return oldItem == newItem

        return false
    }
}
