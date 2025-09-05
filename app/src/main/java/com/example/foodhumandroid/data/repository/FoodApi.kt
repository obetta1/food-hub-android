package com.example.foodhumandroid.data.repository

import com.example.foodhumandroid.data.models.SignInResquest
import com.example.foodhumandroid.data.models.SignUpRequest
import com.example.foodhumandroid.data.models.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodApi {
    @GET("/food")
    suspend fun getListOfFood():List<Int>?{
        return null
    }

    @POST("/signup")
    suspend fun signUp(@Body request: SignUpRequest): retrofit2.Response<SignUpResponse>
  @POST("/signin")
    suspend fun signIn(@Body request: SignInResquest): retrofit2.Response<SignUpResponse>

}