package com.example.foodhumandroid.data

import retrofit2.http.GET

interface FoodApi {
    @GET("/food")
    suspend fun getListOfFood():List<Int>?{
        return null
    }

}