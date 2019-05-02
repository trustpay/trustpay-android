package com.trustpay

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient{

    companion object {
        private val baseUrl:String = "http://10.0.2.2:8000/api/v1/"
        /**
         *  create Instance retrofit
         */
        fun get():Retrofit{
            return Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(addInterceptor())
                .build()
        }

        private fun addInterceptor(): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .build()
                    chain.proceed(newRequest)
                }

            if (BuildConfig.DEBUG){
                builder.addInterceptor(OkHttpProfilerInterceptor())
            }

            return builder.build()

        }


    }
}