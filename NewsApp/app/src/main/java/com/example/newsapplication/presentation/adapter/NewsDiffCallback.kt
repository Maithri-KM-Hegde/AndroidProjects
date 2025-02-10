package com.example.newsapplication.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapplication.domain.NewsHeadline

class NewsDiffCallback : DiffUtil.ItemCallback<NewsHeadline>() {
    override fun areItemsTheSame(oldItem: NewsHeadline, newItem: NewsHeadline): Boolean {
        return oldItem.title == newItem.title  // Assuming title is unique
    }

    override fun areContentsTheSame(oldItem: NewsHeadline, newItem: NewsHeadline): Boolean {
        return oldItem == newItem
    }
}