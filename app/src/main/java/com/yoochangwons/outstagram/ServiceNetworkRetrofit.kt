package com.yoochangwons.outstagram

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ServiceNetworkRetrofit {

    @POST("user/signup/")
    @FormUrlEncoded
    fun createUser(
        @Field("username")username: String,
        @Field("password1")password1: String,
        @Field("password2")password2: String
    ) : Call<User>

    @POST("/user/login/")
    @FormUrlEncoded
    fun login(
        @Field("username")username: String,
        @Field("password")password1: String
    ) : Call<User>

    @GET("/instagram/post/list/all/")
    fun getAllPosts(): Call<ArrayList<Post>>

    @Multipart
    @POST("/instagram/post")
    fun upLoadPost(
        @Part image: MultipartBody.Part,
        @Part("content") requestBody: RequestBody
    ) : Call<Post>
}