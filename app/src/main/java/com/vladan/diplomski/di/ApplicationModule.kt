package com.vladan.diplomski.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.vladan.diplomski.BuildConfig
import com.vladan.diplomski.repository.Repository
import com.vladan.diplomski.repository.network.ApiService
import com.vladan.diplomski.repository.network.HeadersInterceptor
import com.vladan.diplomski.repository.network.RefreshAuthenticator
import com.vladan.diplomski.repository.pref.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun providePreferences(): Preferences = Preferences

    @Provides
    @Singleton
    fun provideOkHttp(
        @ApplicationContext c: Context,
        headersInterceptor: HeadersInterceptor,
        authenticator: RefreshAuthenticator
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(headersInterceptor)
        authenticator(authenticator)
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        }
    }.build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://dev.diplomski.app/")
        .addConverterFactory(
            MoshiConverterFactory.create(moshi).asLenient().withNullSerialization()
        )
        .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesAuth(): RefreshAuthenticator = RefreshAuthenticator()

    @Provides
    @Singleton
    fun provideInterceptor(preferences: Preferences): HeadersInterceptor =
        HeadersInterceptor(preferences)


    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService, preferences: Preferences): Repository =
        Repository(apiService, preferences)

}