package com.example.giphyapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyapi.data.api.ApiResult
import com.example.giphyapi.databinding.ItemCardBinding


class GiphyAdapter(private val gifClickListener: GifClickListener) :
    ListAdapter<ApiResult, GiphyAdapter.ViewHolder>(GiphyDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, gifClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class GifClickListener(val gifClickListener: (gif: ApiResult) -> Unit) {
        fun onClick(gif: ApiResult) = gifClickListener(gif)
    }

    class ViewHolder private constructor(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ApiResult, clickListener: GifClickListener) {
            itemView.context.resources
            binding.clickListener = clickListener
            binding.item = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCardBinding
                    .inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class GiphyDiffCallback : DiffUtil.ItemCallback<ApiResult>() {
        override fun areItemsTheSame(oldItem: ApiResult, newItem: ApiResult): Boolean {
            return oldItem.title == newItem.title
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ApiResult, newItem: ApiResult): Boolean {
            return oldItem == newItem
        }
    }
}