package com.vladan.diplomski.repository.network

import com.vladan.diplomski.model.requests.AddArticleRequest
import com.vladan.diplomski.model.requests.ChangeStatusOfSupplier
import com.vladan.diplomski.model.requests.LoginRequest
import com.vladan.diplomski.model.requests.RegisterRequest
import com.vladan.diplomski.model.requests.RemoveArticleRequest
import com.vladan.diplomski.model.requests.UpdateOrderElementRequest
import com.vladan.diplomski.model.responses.ArticlesResponse
import com.vladan.diplomski.model.responses.CartResponse
import com.vladan.diplomski.model.responses.DefaultResponse
import com.vladan.diplomski.model.responses.HistoryResponse
import com.vladan.diplomski.model.responses.LoginResponse
import com.vladan.diplomski.model.responses.SuppliersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): LoginResponse

    @POST("auth/refresh")
    suspend fun refresh(@Body body: Map<String, String>): LoginResponse

    @GET("articles")
    suspend fun getArticles(): ArticlesResponse

    @POST("cart/add")
    suspend fun addArticleToCart(@Body request: AddArticleRequest): DefaultResponse

    @POST("cart/delete")
    suspend fun deleteArticleFromCart(@Body request: RemoveArticleRequest): DefaultResponse

    @PUT("cart/edit")
    suspend fun editCartElement(@Body request: UpdateOrderElementRequest): DefaultResponse

    @GET("suppliers")
    suspend fun getSuppliers(): SuppliersResponse

    @POST("suppliers/status")
    suspend fun changeStatusOfSupplier(@Body request: ChangeStatusOfSupplier): DefaultResponse

    @GET("cart")
    suspend fun getCart(): CartResponse

    @GET("history")
    suspend fun getHistory(): HistoryResponse
}