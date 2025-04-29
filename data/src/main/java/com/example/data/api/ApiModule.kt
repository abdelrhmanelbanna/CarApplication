package com.example.data.api

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }




    @Provides
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{
        val logging = HttpLoggingInterceptor { message ->
            Log.e("api", message)
        }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return client

    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ):Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl("https://emotive-api.creteagency.com/api/")
            .build()
    }

    @Provides
    fun provideWebServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }

}