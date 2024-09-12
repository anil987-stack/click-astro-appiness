package com.android.appinesstask.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.appinesstask.model.ProductModel

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductModel>)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductModel>
}