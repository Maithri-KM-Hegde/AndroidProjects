package com.example.newsapplication.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.databinding.NewsFragmentBinding
import com.example.newsapplication.domain.NewsHeadline
import com.example.newsapplication.presentation.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TopHeadlinesFragment : Fragment() {
    private val newsViewModel: NewsViewModel by viewModels()
    private var binding: NewsFragmentBinding? = null
    private lateinit var newsAdapter: NewsAdapter

    companion object {
        private const val TAG = "TopHeadlinesFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = NewsFragmentBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchNews()
    }

    private fun initViews() {
        newsAdapter = NewsAdapter { item ->
            Toast.makeText(context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
        }
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
            setHasFixedSize(true)
        }
    }

    private fun fetchNews() {
        newsViewModel.fetchTopHeadLines()
        newsViewModel.newsUiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleNewsState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleNewsState(state: NewsUiState) {
        when (state) {
            is NewsUiState.Init -> Unit
            is NewsUiState.SuccessState -> displayNewsList(state.newsList)
            is NewsUiState.ErrorState -> handleError(state.message)
        }
    }

    private fun handleError(message: String) {
        Log.d(TAG, "handleError: API Failed -> $message")
    }

    private fun displayNewsList(newsList: List<NewsHeadline>) {
        newsAdapter.submitList(newsList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}