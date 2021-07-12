package com.yoochangwons.outstagram

import android.app.Application
import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MasterApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun createNetworkRetrofit() {
        val header = Interceptor {
            val original = it.request()
            val header = original.newBuilder()
                .header("Authorization", "")
                .build()
            it.proceed(header)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(header)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun userIsLoginCheck(): Boolean {

    }

    fun getUserToken(): String? {
        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val token = sp.getString("login_sp", "null")

        return if (token != "null") token
        else null
    }
}