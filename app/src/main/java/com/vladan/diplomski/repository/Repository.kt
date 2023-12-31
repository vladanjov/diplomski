package com.vladan.diplomski.repository

import com.vladan.diplomski.model.Article
import com.vladan.diplomski.model.OrderElement
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
import com.vladan.diplomski.repository.network.ApiService
import com.vladan.diplomski.repository.network.Result
import com.vladan.diplomski.repository.pref.Preferences

class Repository(private val apiService: ApiService, private val preferences: Preferences) {

    suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse> {
        return try {
            val response = apiService.login(
                LoginRequest(
                    email = email,
                    password = password,
                )
            )
            preferences.accessToken = response.token
            preferences.refreshToken = response.refreshToken
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun register(
        name: String,
        address: String,
        pib: String,
        email: String,
        password: String
    ): Result<LoginResponse> {
        return try {
            val response = apiService.register(
                RegisterRequest(
                    name,
                    address,
                    pib,
                    email,
                    password
                )
            )
            preferences.accessToken = response.token
            preferences.refreshToken = response.refreshToken
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun refresh(
    ): Result<LoginResponse> {
        return try {
            val response = apiService.refresh(
                mapOf(Pair("refresh_token", preferences.refreshToken ?: ""))
            )
            preferences.accessToken = response.token
            preferences.refreshToken = response.refreshToken
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getArticles(
    ): Result<ArticlesResponse> {
        return try {
            val response = apiService.getArticles()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun addArticleToCart(
        article: Article
    ): Result<DefaultResponse> {
        return try {
            val response =
                apiService.addArticleToCart(AddArticleRequest(article))
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun deleteArticleFromCart(
        article: Article
    ): Result<DefaultResponse> {
        return try {
            val response =
                apiService.deleteArticleFromCart(RemoveArticleRequest(article))
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun editCartElement(
        element: OrderElement
    ): Result<DefaultResponse> {
        return try {
            val response =
                apiService.editCartElement(UpdateOrderElementRequest(element))
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getSuppliers(
    ): Result<SuppliersResponse> {
        return try {
            val response = apiService.getSuppliers()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun changeStatusOfSupplier(
        supplierId: String,
        status: Boolean
    ): Result<DefaultResponse> {
        return try {
            val response =
                apiService.changeStatusOfSupplier(ChangeStatusOfSupplier(supplierId, status))
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getCart(
    ): Result<CartResponse> {
        return try {
            val response = apiService.getCart()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getHistory(
    ): Result<HistoryResponse> {
        return try {
            val response = apiService.getHistory()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }


}