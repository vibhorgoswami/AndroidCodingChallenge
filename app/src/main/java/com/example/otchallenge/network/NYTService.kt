package com.example.otchallenge.network

import com.example.otchallenge.network.wrapper.NYTResponse
import retrofit2.http.GET

interface NYTService {
    @GET("lists/current/hardcover-fiction.json?offset=0")
    suspend fun getBooks(): NYTResponse
}
