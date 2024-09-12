package com.android.appinesstask.dao


import android.util.Log
import com.android.appinesstask.model.ImagePath
import com.android.appinesstask.model.ProductModel
import com.android.appinesstask.networking.RetrofitInstance

class ProductRepository(private val productDao: ProductDao) {


    suspend fun getAllProducts(): List<ProductModel> {
        return productDao.getAllProducts()
    }


    suspend fun syncProducts() {
        try {
            val apiResponse = RetrofitInstance.api.getProducts()
            val productList = apiResponse.products.map { (id, productData) ->
                ProductModel(
                    id = id,
                    name = productData.name,
                    description = productData.description,
                    availableLanguages = productData.availableLanguages,
                    pages = productData.pages,
                    pagesintext = productData.pagesintext,
                    report_type = productData.report_type,
                    authentic = productData.authentic,
                    remedies = productData.remedies,
                    vedic = productData.vedic,
                    price = productData.price,
                    discount = productData.discount,
                    appDiscount = productData.appDiscount,
                    couponDiscount = productData.couponDiscount,
                    imagePath = productData.imagePath,
                    soldcount = productData.soldcount,
                    avg = productData.avg

                )
            }

            productDao.insertProducts(productList)
        } catch (e: Exception) {

            Log.e("API Error", "Failed to sync products: ${e.message}")
        }
    }

}
