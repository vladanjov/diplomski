package com.vladan.diplomski.repository.network

import com.vladan.diplomski.repository.pref.Preferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.Locale

class HeadersInterceptor(private val preferences: Preferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder: Request.Builder = chain.request().newBuilder()
            .header("Content-Type", "multipart/form-data")
            .header("Accept", "application/json")
            .header("Localization", Locale.getDefault().language)
            .apply {
                preferences.accessToken?.let {
                    this.header(
                        "Authorization",
                        "Bearer ${it}"
                    )
                }
            }
            .method(chain.request().method, chain.request().body)

        return chain.proceed(requestBuilder.build())
    }
}
