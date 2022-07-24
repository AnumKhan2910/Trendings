package com.example.gittrendings.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.gittrendings.data.TrendingUIData
import com.example.gittrendings.databinding.ItemTrendingBinding

class TrendingDataAdapter constructor(
    private var itemClickListener: (TrendingUIData) -> Unit
) :  ListAdapter<TrendingUIData, RecyclerView.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding = ItemTrendingBinding.inflate (
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrendingViewHolder(binding, itemClickListener)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TrendingViewHolder) {
            holder.onBind(currentList[position])
        }
    }


    class TrendingViewHolder constructor(
        private val binding: ItemTrendingBinding,
        var itemClickListener: (TrendingUIData) -> Unit):
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item : TrendingUIData) {
            binding.run {
                data = item
                root.setOnClickListener {
                    itemClickListener(item)
                }
            }
        }
    }


    /*
     Using diff util for efficient views update
     */
    class DiffCallBack : DiffUtil.ItemCallback<TrendingUIData>() {

        override fun areItemsTheSame(oldItem: TrendingUIData, newItem: TrendingUIData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TrendingUIData, newItem: TrendingUIData): Boolean {
            return oldItem == newItem
        }
    }
}