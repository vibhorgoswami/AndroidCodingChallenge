package com.example.otchallenge.di

import com.example.otchallenge.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyProvider @Inject constructor() {

    val apiKey: String
        get() = BuildConfig.API_KEY
}