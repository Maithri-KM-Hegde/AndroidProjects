package com.example.newsapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.databinding.NewsItemBinding
import com.example.newsapplication.domain.NewsHeadline

class NewsAdapter(private val clickListener: (item: NewsHeadline) -> Unit) :
    ListAdapter<NewsHeadline, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: NewsViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.itemView.context).clear(holder.binding.newsImage)

    }

    inner class NewsViewHolder(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: NewsHeadline) {
            with(binding) {
                newsTitle.text = newsItem.title
                newsDescription.text = newsItem.description
                newsAuthor.text = newsItem.author

                Glide.with(newsImage.context)
                    .load(newsItem.urlToImage)
                    .placeholder(R.drawable.placeholder_image)
                    .into(newsImage)
            }
        }
    }
}