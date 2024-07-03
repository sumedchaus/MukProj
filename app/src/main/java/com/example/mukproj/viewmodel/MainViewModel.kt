package com.example.mukproj.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mukproj.apiservice.ApiService
import com.example.mukproj.model.Product
import com.example.mukproj.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {


   private val apiService = RetrofitInstance.retrofit.create(ApiService::class.java)


    val itemsLiveData = MutableLiveData<List<Product>>()

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        apiService.getImages().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    itemsLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
