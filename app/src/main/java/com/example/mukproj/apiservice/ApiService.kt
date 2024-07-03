package com.example.mukproj.apiservice

import com.example.mukproj.model.Product
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("users")
    fun getImages(): Call<List<Product>>


}
