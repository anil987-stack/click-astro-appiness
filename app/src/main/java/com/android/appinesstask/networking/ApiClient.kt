package com.android.appinesstask.networking


import com.android.appinesstask.model.ApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET("test/products.php")
    suspend fun getProducts(): ApiResponse
}





