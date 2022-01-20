package com.example.githubapp.network

import com.example.githubapp.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    @GET("search/users")
    @Headers("Authorization: token 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")
    suspend fun getUsers(@Query("q") query: String):UserResponse
}