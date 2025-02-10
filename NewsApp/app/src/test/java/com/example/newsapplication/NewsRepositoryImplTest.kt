package com.example.newsapplication

import com.example.newsapplication.data.api.NewsApiService
import com.example.newsapplication.data.dto.Article
import com.example.newsapplication.data.dto.BaseQuery
import com.example.newsapplication.data.dto.BaseResponse
import com.example.newsapplication.data.dto.NewsResponse
import com.example.newsapplication.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {
    // Coroutine Test Rule
    @get:Rule
    val testDispatcher = TestCoroutineRule()
    private lateinit var repository: NewsRepositoryImpl

    @Mock
    private lateinit var apiService: NewsApiService

    @Before
    fun setUp() {
        repository = NewsRepositoryImpl(apiService)
    }

    @Test
    fun fetchTopHeadlinesTest() {
        // Given: Mock API response
        val fakeArticles = listOf(
            Article("John Doe", "Breaking News", "Description", "https://image.url")
        )
        val fakeResponse = NewsResponse(articles = fakeArticles, status = "ok")
        val queryParams = BaseQuery(country = "us").toQueryMap()
        var result: BaseResponse<List<Article>>
        runBlocking {
            // Mock API Call
            `when`(apiService.getTopHeadlines(queryParams)).thenReturn(Response.success(fakeResponse))
            result = repository.fetchTopHeadlines(queryParams).first()
        }
        // Then: Verify the flow emits correct data
        assert(result is BaseResponse.Success)
        assertEquals(1, (result as BaseResponse.Success).data.size)
        assertEquals("John Doe", (result as BaseResponse.Success<List<Article>>).data[0].author)
    }
}