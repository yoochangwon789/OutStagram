package com.yoochangwons.outstagram

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ServiceNetworkRetrofit {

    @POST
    @FormUrlEncoded
    fun createUser(
        @Field("username")username: String,
        @Field("password1")password1: String,
        @Field("password1")password2: String,
    ) : Call<User>
}