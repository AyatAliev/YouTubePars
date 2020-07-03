package com.example.geektechyoutubeparcer.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient() {

    companion object {

        private const val BASE_URL = "http://127.0.0.1:8000/"


        fun create(): YoutubeAPi? {
            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()

            return retrofit.create(YoutubeAPi::class.java)

        }

    }

}