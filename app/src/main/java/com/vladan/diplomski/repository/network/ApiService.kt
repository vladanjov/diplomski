package com.vladan.diplomski.repository.network

import com.vladan.diplomski.model.requests.LoginRequest
import com.vladan.diplomski.model.requests.RegisterRequest
import com.vladan.diplomski.model.responses.ArticlesResponse
import com.vladan.diplomski.model.responses.CartResponse
import com.vladan.diplomski.model.responses.HistoryResponse
import com.vladan.diplomski.model.responses.LoginResponse
import com.vladan.diplomski.model.responses.SuppliersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): LoginResponse

    @POST("auth/refresh")
    suspend fun refresh(@Body body: Map<String, String>): LoginResponse

    @GET("/articles")
    suspend fun getArticles(): ArticlesResponse

    @GET("/suppliers")
    suspend fun getSuppliers(): SuppliersResponse

    @GET("/cart")
    suspend fun getCart(): CartResponse

    @GET("/history")
    suspend fun getHistory(): HistoryResponse
}