package com.mayco.desafiospaceflight.network

import com.mayco.desafiospaceflight.network.response.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v3/articles/")
    fun getNews(
        @Query("_limit") limit: Int,
        @Query("_start") start: Int
    ): Deferred<Response<List<NewsResponse>>>
}