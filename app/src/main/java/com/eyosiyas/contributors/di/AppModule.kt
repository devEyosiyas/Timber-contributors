package com.eyosiyas.contributors.di

import com.eyosiyas.contributors.BuildConfig
import com.eyosiyas.contributors.api.GithubApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/* A module that provides the retrofit instance using dependency injection */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /* Providing the retrofit instance. */
    @Provides
    @Singleton
    fun provideRetrofitInstance(): GithubApi =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        ).client(
            OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build()
        ).build().create(GithubApi::class.java)
}