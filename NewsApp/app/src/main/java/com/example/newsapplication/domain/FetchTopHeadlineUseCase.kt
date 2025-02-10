package com.example.newsapplication.domain

import com.example.newsapplication.data.dto.Article
import com.example.newsapplication.data.dto.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchTopHeadlineUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(queryParams: Map<String, String>): Flow<BaseResponse<List<NewsHeadline>>> =
        newsRepository.fetchTopHeadlines(queryParams).map(::createNewsUIModel)


    private fun createNewsUIModel(newsResponse: BaseResponse<List<Article>>):
            BaseResponse<List<NewsHeadline>> {
        return when (newsResponse) {
            is BaseResponse.Success -> {
                val uiModelList = newsResponse.data.map {
                    it.toUIModel()
                }
                BaseResponse.Success(uiModelList)
            }

            is BaseResponse.Error -> {
                BaseResponse.Error(newsResponse.errorMessage)
            }
        }
    }

    // Extension function to convert Data Model to Domain Model for UI
    private fun Article.toUIModel() = NewsHeadline(
        title = title ?: "",
        author = author ?: "",
        description = description ?: "",
        urlToImage = urlToImage ?: ""
    )
}