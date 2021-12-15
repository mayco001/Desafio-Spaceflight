package com.mayco.desafiospaceflight.repository

import com.mayco.desafiospaceflight.network.ApiService
import com.mayco.desafiospaceflight.network.response.NewsResponse
import com.orhanobut.hawk.Hawk

class NewsRepository(private val apiService: ApiService) {
    suspend fun getNews(limit: Int, start: Int) = apiService.getNews(limit, start).await()

    fun saveNews(news: List<NewsResponse?>) {
        Hawk.put("news", news)
    }

    fun getNews(): List<NewsResponse> {
        return Hawk.get("news")
    }

    fun savaClick(newsResponse: NewsResponse) {
        Hawk.put("Click", newsResponse)
    }

    fun getClick(): NewsResponse {
        return Hawk.get("click")
    }


}