package com.example.otchallenge.network.wrapper

data class NYTResponse(
    val status: String,
    val num_results: Int,
    val results: Results,
)
